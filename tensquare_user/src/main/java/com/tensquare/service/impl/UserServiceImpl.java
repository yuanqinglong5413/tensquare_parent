package com.tensquare.service.impl;

import com.tensquare.model.User;
import com.tensquare.repository.UserRepository;
import com.tensquare.service.UserService;
import com.tensquare.util.IdWorker;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    UserRepository userRepository;
    @Autowired
    IdWorker idWorker;

    //发短信
    @Override
    public void sendSms(String mobile) {
        //验证码
        String code = RandomStringUtils.randomNumeric(6);
        redisTemplate.opsForValue().set("checkcode:"+mobile, code, 6, TimeUnit.HOURS);
        //给用户发一份
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("checkcode", code);
        rabbitTemplate.convertAndSend("sms", map);
        //在控制台显示一份【方便测试】
        System.out.println("验证码为："+code);
    }

    @Override
    public void add(User user) {
        user.setId( idWorker.nextId()+"" );
        userRepository.save(user);
    }
}
