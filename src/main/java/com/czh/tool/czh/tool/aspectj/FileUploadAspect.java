package com.czh.tool.czh.tool.aspectj;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.czh.tool.czh.tool.annotation.FileUpload;
import com.czh.tool.czh.tool.annotation.Prevent;
import com.czh.tool.czh.tool.config.ExceptionConfig;
import com.czh.tool.czh.tool.config.FileUploadConfig;
import com.czh.tool.czh.tool.context.FileUploadContext;
import com.czh.tool.czh.tool.enums.BizCodeEnum;
import com.czh.tool.czh.tool.enums.FileUploadStrategy;
import com.czh.tool.czh.tool.enums.PreventStrategy;
import com.czh.tool.czh.tool.exception.PreventException;
import com.czh.tool.czh.tool.service.FileUploadService;
import com.czh.tool.czh.tool.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 自定义操作防刷
 *
 * @author czh
 */

@Aspect
@Slf4j
@Component
public class FileUploadAspect {


    @Autowired
    private FileUploadContext fileUploadContext;




    /**
     *
     * @param joinPoint 参数
     * @throws Exception 异常
     */
    @Around("@annotation(com.czh.tool.czh.tool.annotation.FileUpload)")
    public Object joinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        String requestStr="";
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getMethod(methodSignature.getName(),
                methodSignature.getParameterTypes());

        FileUpload preventAnnotation = method.getAnnotation(FileUpload.class);
        String methodFullName = method.getDeclaringClass().getName() + method.getName();

        Object[] args = joinPoint.getArgs();
        MultipartFile file = null;

        for (Object arg : args) {
            if (arg instanceof MultipartFile) {
                file = (MultipartFile) arg;
                break;
            }
        }
        String entrance = null;
        if (file != null) {
            entrance = entrance(preventAnnotation, requestStr, file);

        }

        return entrance;
    }


    /**
     *  入口
     * @param fileUpload 文件上传
     * @param request  请求
     * @param file 文件
     * @throws Exception
     */
    private String entrance(FileUpload fileUpload, String request,MultipartFile file) throws Exception {
        FileUploadStrategy strategy = fileUpload.strategy();

        return fileUploadContext.upload(strategy,file);
    }




}



