package com.czh.tool.czh.tool.response;

import com.czh.tool.czh.tool.config.ValidatedSuccessConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author czh
 * @since 1.0
 */
@Component
public class ResponseFactory {
    @Autowired
    private ValidatedSuccessConfig validatedSuccessConfig;

    public <T> ValidatedSuccessConfig<T> newSuccessInstance(T data) {
        return new ValidatedSuccessConfig<>(validatedSuccessConfig.getCode(), validatedSuccessConfig.getMessage(), data);
    }

}

