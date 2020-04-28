package com.yingxue.lesson.redisjedis.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RespVo {
    @ApiModelProperty(value = "用户认证凭证")
    private String token;
    @ApiModelProperty(value = "用户id")
    private String userId;
}
