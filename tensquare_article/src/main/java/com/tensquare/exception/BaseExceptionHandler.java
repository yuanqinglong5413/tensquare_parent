package com.tensquare.exception;

import com.tensquare.util.ResultObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public ResultObject error(CustomException e) {
        e.printStackTrace();
        return new ResultObject(false, e.getStatusCode(), e.getMessage());
    }
}
