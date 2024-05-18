package com.czh.tool.czh.tool.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 *
 * @author czh
 * @since 1.0
 */
@ConfigurationProperties(prefix = "czh.tool.success")
@Component
public class ValidatedSuccessConfig<T> implements Serializable {

    /**
     * 成功返回码
     */
    private Integer code=200;

    /**
     * 验证失败错误信息
     */
    public String message="请求成功";

    private T data;

    public ValidatedSuccessConfig(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ValidatedSuccessConfig() {
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
