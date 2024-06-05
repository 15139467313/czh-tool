package com.czh.tool.czh.tool.exception;

/**
 * 这是一个防刷异常处理类。
 * @author czh
 * @since 1.0
 */

public class PreventException extends RuntimeException{
    private Integer code;

    private String errMsg;

    public PreventException(Integer code, String errMsg){
       super(errMsg);
       this.code=code;
       this.errMsg=errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
}
