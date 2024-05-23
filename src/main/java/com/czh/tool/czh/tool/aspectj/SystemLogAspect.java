package com.czh.tool.czh.tool.aspectj;

import com.alibaba.fastjson.JSON;
import com.czh.tool.czh.tool.annotation.SystemLog;
import com.czh.tool.czh.tool.utils.ip.IpUtils;

import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 自定义操作日志记录注解
 *
 * @author czh
 */

@Aspect
public class SystemLogAspect {
    private static final Logger log = LoggerFactory.getLogger(SystemLogAspect.class);
    /**
     * 计算操作消耗时间
     */
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<Long>("Cost Time");

    /**
     * 设置操作日志切入点，这里介绍两种方式：
     * 1、基于注解切入（也就是打了自定义注解的方法才会切入）
     *
     * ("@annotation(com.czh.aop.annotation.SystemLog)") 2、基于包扫描切入
     * ("@annotation(com.czh.aop.annotation.SystemLog)")
     */
    @Pointcut("@annotation(com.czh.tool.czh.tool.annotation.SystemLog)")//在注解的位置切入代码
    //@Pointcut("execution(public * org.wujiangbo.controller..*.*(..))")//从controller切入
    public void operLogPoinCut() {

    }


    /**
     *
     * @param joinPoint 处理请求前执行
     */
    @Before("operLogPoinCut()")
    public void boBefore(JoinPoint joinPoint) {

        //为了记录方法的执行时间
        TIME_THREADLOCAL.set(System.currentTimeMillis());
        ThreadContext.put("traceId", UUID.randomUUID().toString());
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            SystemLog myLog = method.getAnnotation(SystemLog.class);

            ThreadContext.put("traceId", UUID.randomUUID().toString());
            // 将入参转换成json
            String params = argsArrayToString(joinPoint.getArgs());
            log.info("=================start================");
            log.info("入口模块:" + myLog.title());
            log.info("入口功能:" + myLog.content());
            log.info("IP:" + IpUtils.getIpAddr(request));
            log.info("入口参数=" + params);


        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("异常信息:{}", exp);
            exp.printStackTrace();
        }

    }


    /**
     *
     * @param joinPoint 切点
     * @param jsonResult 切果
     */
    @AfterReturning(value = "operLogPoinCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult)
    {
        try {
            // 设置消耗时间
            log.info("消耗时间(ms)" + String.valueOf(System.currentTimeMillis() - TIME_THREADLOCAL.get()));
            log.info("=================end============");
        } finally {
            TIME_THREADLOCAL.remove();
        }
    }

    /**
     * @param: 参数paramsArray
     * @return: String
     * @description:拼接参数
     * @author: czh
     * @date: 2023/12/24 21:16
     **/
    private String argsArrayToString(Object[] paramsArray) {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                if (o != null) {
                    try {
                        Object jsonObj = JSON.toJSON(o);
                        params += jsonObj.toString() + " ";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return params.trim();
    }



}
