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
public @interface FileDown {

    /**
     * 文件名称
     *
     * @return 文件名称路径(此路径是配置下载路径之后）
     */
    String fileName();

    /**
     * 策略
     *
     * @return 默认策略(图片）
     */
    FileStrategy strategy() default FileStrategy.DEFAULT;



    /**
     * 是否在线查看
     *
     * @return 默认策略(图片）
     */
    boolean checkSee() default true;


}
