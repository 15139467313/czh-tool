package com.czh.tool.czh.tool.aspectj;

import com.czh.tool.czh.tool.annotation.Prevent;
import com.czh.tool.czh.tool.config.ExceptionConfig;
import com.czh.tool.czh.tool.enums.BizCodeEnum;
import com.czh.tool.czh.tool.enums.PreventStrategy;

import com.czh.tool.czh.tool.exception.PreventException;
import com.czh.tool.czh.tool.utils.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

/**
 * 自定义操作防刷
 *
 * @author czh
 */

@Aspect
public class PreventAspect {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ExceptionConfig exceptionConfig;


    /**
     * 切入点
     */
    @Pointcut("@annotation(com.czh.tool.czh.tool.annotation.Prevent)")
    public void pointcut() {
    }


    /**
     *
     * @param joinPoint 参数
     * @throws Exception 异常
     */
    @Before("pointcut()")
    public void joinPoint(JoinPoint joinPoint) throws Exception {
        String requestStr="";
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
     * @param prevent 参数
     * @param requestStr 返回
     */
    private void entrance(Prevent prevent, String requestStr,String methodFullName) throws Exception {
        PreventStrategy strategy = prevent.strategy();
        switch (strategy) {
            case DEFAULT:
                defaultHandle(requestStr, prevent,methodFullName);
                break;
            default:
                throw new Exception(BizCodeEnum.INVALIDSTRATEGY_EXCEPTION.getMessage());
        }
    }


    /**
     * 默认处理方式
     *
     * @param requestStr 请求参数
     * @param prevent 放刷
     */
    private void defaultHandle(String requestStr, Prevent prevent,String methodFullName) throws Exception {
        String base64Str = toBase64String(requestStr);
        long expire = Long.parseLong(prevent.value());
        Boolean hasKey = redisTemplate.hasKey(methodFullName + base64Str);
        if (!hasKey){
            redisTemplate.opsForValue().set(methodFullName+base64Str, requestStr, expire, TimeUnit.MILLISECONDS);
        } else {
            String message = !StringUtils.isEmpty(prevent.message()) ? prevent.message() :
                    expire + "毫秒内不允许重复请求";
            throw new PreventException(exceptionConfig.getCode(), message);
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



