package com.czh.tool.czh.tool.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 *
 * @author czh
 * @since 1.0
 */
@ConfigurationProperties(prefix = "czh.tool")
@Component
public class ValidatedExceptionConfig implements Serializable {

    /**
     * 验证失败错误码
     */
    private Integer code;

    /**
     * 验证失败错误信息
     */
    public String message;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
