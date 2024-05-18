package com.czh.tool.czh.tool.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @ClassNAME ValidatedExceptionConfig
 * @Description TODO
 * @Author czh
 * @Date 2024/5/18 10:01
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "czh.tool")
@Component
public class ValidatedExceptionConfig implements Serializable {

    /**
     * 验证失败错误码
     */
    private String code;

    /**
     * 验证失败错误信息
     */
    public String message;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
