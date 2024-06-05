package com.czh.tool.czh.tool.config;

import com.czh.tool.czh.tool.aspectj.FileUploadAspect;
import com.czh.tool.czh.tool.aspectj.PreventAspect;
import com.czh.tool.czh.tool.aspectj.SystemLogAspect;
import com.czh.tool.czh.tool.context.FileUploadContext;
import com.czh.tool.czh.tool.exception.CzhExceptionControllerAdvice;
import com.czh.tool.czh.tool.exception.GrNotVoidResponseBodyAdvice;
import com.czh.tool.czh.tool.response.ResponseFactory;
import com.czh.tool.czh.tool.service.FileUploadService;
import com.czh.tool.czh.tool.service.impl.DefaultUploadServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolFileAutoConfig {

    @Bean
    public FileUploadContext fileUploadContext() {
        return new FileUploadContext();
    }

    @Bean
    public DefaultUploadServiceImpl defaultUploadService() {
        return new DefaultUploadServiceImpl();
    }

    @Bean
    public FileUploadConfig fileUploadConfig() {
        return new FileUploadConfig();
    }

    @Bean
    public WebMvcConfig webMvcConfig() {
        return new WebMvcConfig();
    }


}

