package com.seven.dust.responses;

/**
 * 统一返回json格式化对象结构
 */

public class RestRep<T> {
    private int code;
    private String message;
    private T data;

    public RestRep(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}