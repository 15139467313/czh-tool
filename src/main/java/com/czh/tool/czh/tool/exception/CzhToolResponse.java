package com.czh.tool.czh.tool.exception;



/**
 * CzhToolResponse工具类
 */
public class CzhToolResponse {

    /**
     * 需要抛自定义异常时，调用该方法
     *
     * @param code 异常码
     * @param msg  异常提示
     */
    public static void raiseException(int code, String msg) {
        throw new CzhToolResponseException(code, msg);
    }

    /**
     * 需要抛自定义异常时，调用该方法
     *
     * @param code      异常码
     * @param msg       异常提示
     * @param throwable 捕获的异常
     */
    public static void raiseException(int code, String msg, Throwable throwable) {
        throw new CzhToolResponseException(code, msg, throwable);
    }


}
