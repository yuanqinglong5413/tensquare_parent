package com.tensquare.config;


import com.tensquare.util.ResultObject;
import com.tensquare.util.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@ControllerAdvice
public class RecruitExceptionHandler {

    //移除处理方法
    @ResponseBody
    @ExceptionHandler({RuntimeException.class, IOException.class})
    public ResultObject handlerException(Exception e){

        //记录日志
        System.out.println(e.toString());
        return new ResultObject(StatusCode.ERROR,"请求失败",false);
    }
}
