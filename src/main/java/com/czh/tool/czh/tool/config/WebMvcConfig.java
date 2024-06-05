package com.czh.tool.czh.tool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 路径映射
 *
 * @author czh
 */


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private FileUploadConfig fileUploadConfig;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/"+fileUploadConfig.getProjectName()+"/**").addResourceLocations("file:"+fileUploadConfig.getFileUploadPath()+"/");
        registry.addResourceHandler("/static/**").addResourceLocations("file:"+fileUploadConfig.getFileUploadPath()+"/");




    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                String requestURI = request.getRequestURI();
                String[] parts = requestURI.split("/");
                if (parts.length >= 3) {
                    String dynamicPart = parts[2]; // Assuming the dynamic part is the third part of the URI
                    String filePath = fileUploadConfig.getFileUploadPath() + dynamicPart + "/";
                    request.setAttribute("filePath", filePath);
                }
                return true;
            }
        });
    }
}

