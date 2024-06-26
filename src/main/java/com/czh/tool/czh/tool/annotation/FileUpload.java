package com.czh.tool.czh.tool.annotation;

/**
 * @ClassNAME FileUpload
 * @Description TODO
 * @Author czh
 * @Date 2024/6/5 14:37
 * @Version 1.0
 */

import com.czh.tool.czh.tool.enums.FileStrategy;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FileUpload {

    /**
     * 策略
     *
     * @return 默认策略(图片）
     */
    FileStrategy strategy() default FileStrategy.DEFAULT;
}
