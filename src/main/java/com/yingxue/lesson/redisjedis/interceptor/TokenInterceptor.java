package com.yingxue.lesson.redisjedis.interceptor;

import com.yingxue.lesson.redisjedis.exception.BusinessException;
import com.yingxue.lesson.redisjedis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)){
            throw  new BusinessException(400101,"用户凭证不能为空");
        }else {
            if (!redisService.hasKey(token)){
                throw new BusinessException(4002003,"用户凭证无效");
            }
            String userId = (String) redisService.get(token);
            if (redisService.hasKey(userId)&&!token.equals(redisService.get(userId))){
                throw new BusinessException(400101,"您的账号已在异地登录，请重新登录");
            }
        }
        return true;
    }
}
