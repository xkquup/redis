package com.yingxue.lesson.redisjedis.service.Impl;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.yingxue.lesson.redisjedis.contants.Contant;
import com.yingxue.lesson.redisjedis.dao.SysUserMapper;
import com.yingxue.lesson.redisjedis.entity.SysUser;
import com.yingxue.lesson.redisjedis.exception.BusinessException;
import com.yingxue.lesson.redisjedis.service.RedisService;
import com.yingxue.lesson.redisjedis.service.UserService;
import com.yingxue.lesson.redisjedis.utils.PasswordUtils;
import com.yingxue.lesson.redisjedis.vo.Req.RegisterReqVo;
import com.yingxue.lesson.redisjedis.vo.Req.ReqVo;
import com.yingxue.lesson.redisjedis.vo.resp.RespVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public RespVo login(ReqVo vo) {
        SysUser user = userMapper.selectByName(vo.getName());
        if (null==user){
            throw new BusinessException(400,"用户不存在");
        }
        if (user.getStatus()==0){
            throw new BusinessException(4001,"用户被禁用");
        }
        if (!PasswordUtils.matches(user.getSalt(),vo.getPwd(),user.getPassword())){
            throw new BusinessException(4002,"密码错误");
        }

        String token=UUID.randomUUID().toString();
        RespVo respVo = new RespVo();
        respVo.setUserId(user.getId());
        respVo.setToken(token);
        redisService.set(token,user.getId(),60, TimeUnit.MINUTES);
        redisService.set(user.getId(),token,60,TimeUnit.MINUTES);
        return respVo;
    }

    @Override
    public SysUser getUserInfo(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public String register(RegisterReqVo vo) {
        if (!redisService.hasKey(Contant.REGISTER_CODE_COUNT_VALIDITY_KEY+vo.getPhone())){
            throw  new BusinessException(400102,"验证码失效");
        }
        if(!vo.getCode().equals(redisService.get(Contant.REGISTER_CODE_COUNT_VALIDITY_KEY+vo.getPhone()))){
            throw new BusinessException(400102,"验证码不正确");
        }
        SysUser user1 = userMapper.selectByName(vo.getUsername());
        if (user1!=null){
            throw new BusinessException(400102,"用户被占用");
        }
        SysUser user=new SysUser();
        BeanUtils.copyProperties(vo,user);
        user.setId(UUID.randomUUID().toString());
        user.setCreateTime(new Date());
        String salt = PasswordUtils.getSalt();
        String encode = PasswordUtils.encode(vo.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(encode);
        int i = userMapper.insertSelective(user);
        if (i!=1){
            throw  new BusinessException(4001011,"操作失败");
        }
        redisService.delete(Contant.REGISTER_CODE_COUNT_VALIDITY_KEY+vo.getPhone());
        redisService.delete(Contant.REGISTER_CODE_COUNT_KEY+vo.getPhone());
        return "注册成功";
    }

    @Override
    public String getCode(String phone) {
        //验证手机号是否合法
        Pattern pattern = Pattern.compile("^1[3|4|5|7|8][0-9]{9}$");
        Matcher matcher = pattern.matcher(phone);
        if(!matcher.matches()) {
            throw  new BusinessException(4001004,"手机号格式错误");
        }
        //判断手机号是否超限
        long count = redisService.incrby(Contant.REGISTER_CODE_COUNT_KEY+phone,1);
        if(count>5){
            throw new BusinessException(4001004,"当日发送已达上限");
        }
        //生成6位随机数
        String code=generateCode();
        //发送短信(具体根据你们公司所用的api文档来)
        //存入 redis 过期时间为 5 分钟
        redisService.set(Contant.REGISTER_CODE_COUNT_VALIDITY_KEY+phone,code,5,TimeUnit.MINUTES);
        //发送短信这里用输出模拟
        System.out.println(code);
        return code;
    }

    /**
     * 生成六位验证码
     * @return
     */
    private String generateCode(){
        Random random = new Random();
        int x = random.nextInt(899999);
        String code = String.valueOf(x + 100000);
        return code;
    }
}
