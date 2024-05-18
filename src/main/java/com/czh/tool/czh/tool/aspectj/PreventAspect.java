package com.czh.tool.czh.tool.aspectj;

import com.alibaba.fastjson.JSON;

import com.czh.tool.czh.tool.annotation.Prevent;
import com.czh.tool.czh.tool.enums.PreventStrategy;

import com.czh.tool.czh.tool.utils.StringUtils;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

/**
 * @ClassNAME PreventAspect
 * @Description TODO
 * @Author czh
 * @Date 2024/1/9 09:16
 * @Version 1.0
 */

@Aspect
@Component
public class PreventAspect {
    private static Logger log = LoggerFactory.getLogger(PreventAspect.class);

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 切入点
     */
    @Pointcut("@annotation(com.czh.tool.czh.tool.annotation.Prevent)")
    public void pointcut() {
    }


    /**
     * 处理前
     *
     * @return
     */
    @Before("pointcut()")
    public void joinPoint(JoinPoint joinPoint) throws Exception {
        String requestStr="";
        if (StringUtils.isEmpty(joinPoint.getArgs())){
             requestStr = JSON.toJSONString(joinPoint.getArgs()[0]);
            if (StringUtils.isEmpty(requestStr) || requestStr.equalsIgnoreCase("{}")) {
                throw new CzhException(BizCodeEnum.PAPRM_EXCEPTION.getCode(),BizCodeEnum.PAPRM_EXCEPTION.getMessage());
            }
        }

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getMethod(methodSignature.getName(),
                methodSignature.getParameterTypes());

        Prevent preventAnnotation = method.getAnnotation(Prevent.class);
        String methodFullName = method.getDeclaringClass().getName() + method.getName();

        entrance(preventAnnotation, requestStr,methodFullName);
        return;
    }


    /**
     * 入口
     *
     * @param prevent
     * @param requestStr
     */
    private void entrance(Prevent prevent, String requestStr,String methodFullName) throws Exception {
        PreventStrategy strategy = prevent.strategy();
        switch (strategy) {
            case DEFAULT:
                defaultHandle(requestStr, prevent,methodFullName);
                break;
            default:
                throw new CzhException(BizCodeEnum.INVALIDSTRATEGY_EXCEPTION.getCode(),BizCodeEnum.INVALIDSTRATEGY_EXCEPTION.getMessage());
        }
    }


    /**
     * 默认处理方式
     *
     * @param requestStr
     * @param prevent
     */
    private void defaultHandle(String requestStr, Prevent prevent,String methodFullName) throws Exception {
        String base64Str = toBase64String(requestStr);
        long expire = Long.parseLong(prevent.value());
        Boolean hasKey = redisTemplate.hasKey(methodFullName + base64Str);
        if (!hasKey){
            redisTemplate.opsForValue().set(methodFullName+base64Str, requestStr, expire, TimeUnit.SECONDS);
        } else {
            String message = !StringUtils.isEmpty(prevent.message()) ? prevent.message() :
                    expire + "秒内不允许重复请求";
            throw new CzhException(BizCodeEnum.BRUSHPROOF_EXCEPTION.getCode(),message);
        }
    }


    /**
     * 对象转换为base64字符串
     *
     * @param obj 对象值
     * @return base64字符串
     */
    private String toBase64String(String obj) throws Exception {
        if (StringUtils.isEmpty(obj)) {
            return null;
        }
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] bytes = obj.getBytes("UTF-8");
        return encoder.encodeToString(bytes);
    }
}



