package com.tensquare.util;

public class ResultObject {

    private Integer code;
    private String message;
    private boolean flag;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public ResultObject setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultObject setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isFlag() {
        return flag;
    }

    public ResultObject setFlag(boolean flag) {
        this.flag = flag;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResultObject setData(Object data) {
        this.data = data;
        return this;
    }

    public ResultObject() {
    }

    public ResultObject(Integer code, String message, boolean flag, Object data) {
        this.code = code;
        this.message = message;
        this.flag = flag;
        this.data = data;
    }

    public ResultObject(Integer code, String message, boolean flag) {
        this.code = code;
        this.message = message;
        this.flag = flag;
    }
}
