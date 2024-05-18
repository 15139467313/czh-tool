package com.czh.tool.czh.tool.config;

import com.czh.tool.czh.tool.exception.CzhExceptionControllerAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatedExceptionAutoConfig {

    @Bean
    public CzhExceptionControllerAdvice controllerAdvice() {
        return new CzhExceptionControllerAdvice();
    }

    @Bean
    public ValidatedExceptionConfig validatedExceptionConfig() {
        return new ValidatedExceptionConfig();
    }

}
