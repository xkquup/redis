package com.yingxue.lesson.redisjedis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testC {

    @RequestMapping("hello")
    public String h(){
        return "hhhh";

    }
}
