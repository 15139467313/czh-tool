package com.czh.tool.czh.tool.exception;

/**
 * @ClassNAME CzhException
 * @Description 自定义防刷异常
 * @Author czh
 * @Date 2024/1/9 09:43
 * @Version 1.0
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
