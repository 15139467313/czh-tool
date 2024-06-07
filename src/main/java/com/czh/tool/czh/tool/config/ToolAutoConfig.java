package com.czh.tool.czh.tool.config;

import com.czh.tool.czh.tool.aspectj.FileUploadAspect;
import com.czh.tool.czh.tool.aspectj.PreventAspect;
import com.czh.tool.czh.tool.aspectj.SystemLogAspect;
import com.czh.tool.czh.tool.exception.CzhExceptionControllerAdvice;
import com.czh.tool.czh.tool.exception.CzhToolResponse;
import com.czh.tool.czh.tool.exception.CzhToolResponseException;
import com.czh.tool.czh.tool.exception.GrNotVoidResponseBodyAdvice;
import com.czh.tool.czh.tool.response.ResponseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolAutoConfig {

    @Bean
    public CzhExceptionControllerAdvice controllerAdvice() {
        return new CzhExceptionControllerAdvice();
    }

    @Bean
    public GlobalResponseHandlerConfig globalResponseHandler() {
        return new GlobalResponseHandlerConfig();
    }

    @Bean
    public ExceptionConfig exceptionConfig() {
        return new ExceptionConfig();
    }

    @Bean
    public ValidatedExceptionConfig validatedExceptionConfig() {
        return new ValidatedExceptionConfig();
    }

    @Bean
    public ValidatedSuccessConfig validatedSuccessConfig() {
        return new ValidatedSuccessConfig();
    }

    @Bean
    public ResponseFactory responseFactory() {
        return new ResponseFactory();
    }

    @Bean
    public GrNotVoidResponseBodyAdvice grNotVoidResponseBodyAdvice() {
        return new GrNotVoidResponseBodyAdvice();
    }


    @Bean
    public SystemLogAspect systemLogAspect() {
        return new SystemLogAspect();
    }

    @Bean
    public PreventAspect preventAspect() {
        return new PreventAspect();
    }

    @Bean
    public FileUploadAspect fileUploadAspect() {
        return new FileUploadAspect();
    }


    @Bean
    public CzhToolResponseException czhToolResponseException() {
        return new CzhToolResponseException();
    }


    @Bean
    public CzhToolResponse czhToolResponse() {
        return new CzhToolResponse();
    }


}

