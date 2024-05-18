package com.czh.tool.czh.tool.annotation;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 *
 * @author czh
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    /**
     * 模块
     */
    public String title() default "";

    /**
     * 功能
     */
    public String content() default "";
}
