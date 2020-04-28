package com.yingxue.lesson.redisjedis;

import com.yingxue.lesson.redisjedis.entity.User;
import com.yingxue.lesson.redisjedis.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisJedisApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private RedisService redisService;

    @Test
    public void testRedis(){
        //redisTemplate.opsForValue().get("name");
       //System.out.println(redisTemplate.opsForValue().get("dd"));
       // redisTemplate.setKeySerializer(new StringRedisSerializer());
       // redisTemplate.setValueSerializer(new StringRedisSerializer());
       // redisTemplate.opsForValue().set("num","1");
        /*User user = new User();
        user.setUsername("xiaopeng");
        user.setPwd("1234");*/
        //redisTemplate.opsForValue().set("u",user);
        //redisService.set("u",user);
        //System.out.println(redisService.hasKey("num"));
       // System.out.println(redisTemplate.opsForValue().get("num"));
    }

}
