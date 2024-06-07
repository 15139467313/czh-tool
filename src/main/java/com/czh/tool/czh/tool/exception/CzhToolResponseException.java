package com.czh.tool.czh.tool.exception;

import com.czh.tool.czh.tool.annotation.ExceptionMapper;

public class CzhToolResponseException extends RuntimeException {

    /**
     * 响应码
     */
    private int code;
    /**
     * 提示信息
     */
    private String msg;

    public CzhToolResponseException() {
    }

    public CzhToolResponseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public CzhToolResponseException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CzhToolResponseException(String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
    }

    public CzhToolResponseException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
