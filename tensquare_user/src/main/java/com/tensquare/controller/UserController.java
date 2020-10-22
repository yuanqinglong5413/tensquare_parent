package com.tensquare.controller;

import com.tensquare.model.User;
import com.tensquare.service.UserService;
import com.tensquare.util.ResultObject;
import com.tensquare.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 发送短信验证码
     */
    @RequestMapping(value = "/sendsms/{mobile}", method = RequestMethod.POST)
    public ResultObject sendSms(@PathVariable String mobile){
        userService.sendSms(mobile);
        return new ResultObject(StatusCode.OK, "发送成功",true);
    }

    @RequestMapping(value = "/register/{code}", method = RequestMethod.POST)
    public ResultObject regist(@PathVariable String code, @RequestBody User user){
        //得到缓存中的验证码
        String checkcodeRedis = (String) redisTemplate.opsForValue().get("checkcode:" + user.getMobile());
        if(checkcodeRedis.isEmpty()){
            return new ResultObject(StatusCode.ERROR, "请先获取手机验证码",false);
        }
        if(!checkcodeRedis.equals(code)){
            return new ResultObject(StatusCode.ERROR, "请输入正确的验证码",false);
        }
        userService.add(user);
        return new ResultObject(StatusCode.OK, "注册成功",true);
    }
}
