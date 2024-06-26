package com.czh.tool.czh.tool.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 *
 * @author czh
 * @since 1.0
 */
@ConfigurationProperties(prefix = "czh.tool.valid.error")
@Component
public class ValidatedExceptionConfig implements Serializable {

    /**
     * 验证失败错误码
     */
    private Integer code=400;

    /**
     * 验证失败错误信息
     */
    public String message="参数校验失败";


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
