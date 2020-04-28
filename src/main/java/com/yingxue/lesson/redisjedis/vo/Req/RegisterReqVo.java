package com.yingxue.lesson.redisjedis.vo.Req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisterReqVo {
    @ApiModelProperty(value = "账号")
    private String username;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "验证码")
    private String code;
    @ApiModelProperty(value = "密码")
    private String password;

}
