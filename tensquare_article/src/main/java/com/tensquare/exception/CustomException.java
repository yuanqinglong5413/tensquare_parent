package com.tensquare.exception;

public class CustomException {

    // 状态码
    private int statusCode;
    // 错误信息
    private String message;

    public CustomException(int statusCode,String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public CustomException() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
