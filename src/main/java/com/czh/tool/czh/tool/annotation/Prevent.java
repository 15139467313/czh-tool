package com.czh.tool.czh.tool.annotation;


import com.czh.tool.czh.tool.enums.PreventStrategy;

import java.lang.annotation.*;

/**
 * 接口防刷注解
 * 使用：
 * 在相应需要防刷的方法上加上
 * 该注解，即可
 *
 * @author: czh
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Prevent {

    /**
     * 限制的时间值（秒）
     *
     * @return 限制
     */
    String value() default "60";

    /**
     * 提示
     * @return 信息提示
     */
    String message() default "";

    /**
     * 策略
     *
     * @return 默认策略
     */
    PreventStrategy strategy()default PreventStrategy.DEFAULT;
}
