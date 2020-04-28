package com.yingxue.lesson.redisjedis.service.Impl;

import com.yingxue.lesson.redisjedis.contants.Contant;
import com.yingxue.lesson.redisjedis.service.CodeService;
import com.yingxue.lesson.redisjedis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    private RedisService redisService;
    @Override
    public String getOrderCode(String type) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
        String date=simpleDateFormat.format(System.currentTimeMillis());
        Long number=redisService.incrby(Contant.ORDER_CODE_KEY+date,1);
        String pad=padRight(number.toString(),7,"0");
        return date+type+pad;
    }

    private String padRight(String oldStr,int len,String alexin){
        String str="";
        int strlen=oldStr.length();
        for(int i=0;i<len-strlen;i++){
            str=str+alexin;
        }
        str=str+oldStr;
        return str;
    }
}
