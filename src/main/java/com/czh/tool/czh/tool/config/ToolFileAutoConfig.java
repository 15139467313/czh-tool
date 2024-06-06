package com.czh.tool.czh.tool.config;

import com.czh.tool.czh.tool.aspectj.FileDownAspect;
import com.czh.tool.czh.tool.context.FileDownContext;
import com.czh.tool.czh.tool.context.FileUploadContext;
import com.czh.tool.czh.tool.service.impl.DefaultUploadServiceImpl;
import com.czh.tool.czh.tool.service.impl.ExcelDownServiceImpl;
import com.czh.tool.czh.tool.service.impl.PdfDownServiceImpl;
import com.czh.tool.czh.tool.service.impl.WordDownServiceImpl;
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

    @Bean
    public FileDownAspect fileDownAspect() {
        return new FileDownAspect();
    }

    @Bean
    public WordDownServiceImpl wordDownServiceImpl() {
        return new WordDownServiceImpl();
    }


    @Bean
    public PdfDownServiceImpl pdfDownServiceImpl() {
        return new PdfDownServiceImpl();
    }


    @Bean
    public ExcelDownServiceImpl excelServiceImpl() {
        return new ExcelDownServiceImpl();
    }


    @Bean
    public FileDownContext fileDownContext() {
        return new FileDownContext();
    }

    @Bean
    public FileDownConfig fileDownConfig() {
        return new FileDownConfig();
    }



}

