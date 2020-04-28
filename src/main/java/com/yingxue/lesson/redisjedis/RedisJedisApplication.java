package com.yingxue.lesson.redisjedis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yingxue.lesson.redisjedis.dao")
public class RedisJedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisJedisApplication.class, args);
    }

}
