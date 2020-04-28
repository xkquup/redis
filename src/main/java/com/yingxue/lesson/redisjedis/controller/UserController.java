package com.yingxue.lesson.redisjedis.controller;

import com.yingxue.lesson.redisjedis.entity.SysUser;
import com.yingxue.lesson.redisjedis.service.UserService;
import com.yingxue.lesson.redisjedis.vo.Req.RegisterReqVo;
import com.yingxue.lesson.redisjedis.vo.Req.ReqVo;
import com.yingxue.lesson.redisjedis.vo.resp.RespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Api(tags = "用户模块",description = "用户模块相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    @ApiOperation(value = "用户登录接口")
    public RespVo login(@RequestBody ReqVo reqVo){
        return userService.login(reqVo);
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "获取用户信息接口")
    public SysUser getUserInfo(@PathVariable String id){
        return userService.getUserInfo(id);
    }

    @PostMapping("/user/register")
    @ApiOperation(value = "用户注册接口")
    public String register(@RequestBody RegisterReqVo reqVo){
        return userService.register(reqVo);
    }

    @GetMapping("/user/code/{phone}")
    @ApiOperation(value = "获取验证码接口")
    public String getCode(@PathVariable("phone") String phone){
        return userService.getCode(phone);
    }
}
