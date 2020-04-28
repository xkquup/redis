package com.yingxue.lesson.redisjedis.config;

import com.yingxue.lesson.redisjedis.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Bean
    public TokenInterceptor tokenInterceptor(){
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor()).addPathPatterns("/api/**").excludePathPatterns("/api/user/login","/api/user/register","/api/user/code/*");
    }
}
