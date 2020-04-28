package com.yingxue.lesson.redisjedis.service;

import com.yingxue.lesson.redisjedis.entity.SysUser;
import com.yingxue.lesson.redisjedis.vo.Req.RegisterReqVo;
import com.yingxue.lesson.redisjedis.vo.Req.ReqVo;
import com.yingxue.lesson.redisjedis.vo.resp.RespVo;

public interface UserService {
    public RespVo login(ReqVo vo);

    public SysUser getUserInfo(String id);

    public String register(RegisterReqVo vo);

    public String getCode(String phone);
}
