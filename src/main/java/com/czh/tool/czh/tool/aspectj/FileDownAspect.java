package com.czh.tool.czh.tool.aspectj;

import com.czh.tool.czh.tool.annotation.FileDown;
import com.czh.tool.czh.tool.annotation.FileUpload;
import com.czh.tool.czh.tool.context.FileDownContext;
import com.czh.tool.czh.tool.context.FileUploadContext;
import com.czh.tool.czh.tool.enums.FileStrategy;
import com.czh.tool.czh.tool.exception.FileException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 自定义操作防刷
 *
 * @author czh
 */

@Aspect
@Slf4j
@Component
public class FileDownAspect {


    @Autowired
    private FileDownContext fileDownContext;




    /**
     *
     * @param joinPoint 参数
     * @throws Exception 异常
     */
    @Around("@annotation(com.czh.tool.czh.tool.annotation.FileDown)")
    public void joinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getMethod(methodSignature.getName(),
                methodSignature.getParameterTypes());

        FileDown preventAnnotation = method.getAnnotation(FileDown.class);

        Object[] args = joinPoint.getArgs();
        HttpServletResponse response = null;

        for (Object arg : args) {
            if (arg instanceof HttpServletResponse) {
                response = (HttpServletResponse) arg;
                break;
            }
        }

        if (response != null) {
         entrance(preventAnnotation.strategy(),preventAnnotation,response);

        }else {
            throw new FileException();
        }

    }



    private void entrance(FileStrategy fileStrategy,FileDown fileDown,HttpServletResponse response) throws Exception {


        fileDownContext.down(fileStrategy,fileDown,response);
    }




}



