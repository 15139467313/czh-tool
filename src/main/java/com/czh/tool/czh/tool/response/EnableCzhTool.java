package com.czh.tool.czh.tool.response;

import com.czh.tool.czh.tool.config.ToolAutoConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


/**
 * 注解启动全局结果处理的入口.
 *
 * @author czh
 * @version 0.1
 * @since 0.1
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(ToolAutoConfig.class)
public @interface EnableCzhTool {
}
