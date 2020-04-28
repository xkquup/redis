package com.yingxue.lesson.redisjedis.vo.Req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;

@Data
public class ReqVo {
    @ApiModelProperty(value = "用户名")
    private String name;
    @ApiModelProperty(value = "密码")
    private String pwd;
}
