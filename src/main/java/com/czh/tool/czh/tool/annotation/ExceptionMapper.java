package com.czh.tool.czh.tool.annotation;


import java.lang.annotation.*;


/**
 * 自定义运行异常
 *
 * @author czh
 */


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExceptionMapper {


    /**
     * 异常对应的错误码.
     *
     * @return 异常对应的错误码
     */
    int code() default 500;

    /**
     * 异常信息.
     *
     * @return 异常对应的提示信息
     */
    String msg() default "Poor network quality!";

}
