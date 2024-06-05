package com.czh.tool.czh.tool.exception;



import com.czh.tool.czh.tool.annotation.ExceptionMapper;
import com.czh.tool.czh.tool.config.ExceptionConfig;
import com.czh.tool.czh.tool.config.ValidatedExceptionConfig;
import com.czh.tool.czh.tool.response.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * 这是一个统一异常处理类。
 * @author czh
 * @since 1.0
 */
@Slf4j
@RestControllerAdvice()
@Order(value = 1000)
public class CzhExceptionControllerAdvice {

    @Autowired
    private ValidatedExceptionConfig validatedExceptionConfig;

    @Autowired
    private ExceptionConfig exceptionConfig;

    /**
     * 参数非法（效验参数）异常 MethodArgumentNotValidException
     * @param e 参数异常类
     * @return 返回错误信息
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AjaxResult handleValidException(MethodArgumentNotValidException e) {
        log.error("数据效验出现问题{},异常类型{}",e.getMessage(),e.getClass());
        BindingResult bindingResult = e.getBindingResult();


        Map<String,String> errMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach((fieldError) -> {
            errMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        return AjaxResult.error(validatedExceptionConfig.getCode(),validatedExceptionConfig.getMessage())
                .put("data",errMap);
    }

    /**
     * 参数非法（效验参数）异常 ConstraintViolationException
     * @param e 参数异常类
     * @return 返回错误信息
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AjaxResult handleValidException(ConstraintViolationException e) {
        log.error("数据效验出现问题{},异常类型{}",e.getMessage(),e.getClass());
       e.getConstraintViolations();
        return AjaxResult.error(validatedExceptionConfig.getCode(),validatedExceptionConfig.getMessage())
                .put("data",e.getMessage());
    }

    /**
     *
     * @param e Throwable 业务逻辑抛出的异常
     * @param request 请求头
     * @return 统一返回包装后的结果
     */
    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AjaxResult handleException(Throwable e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return AjaxResult.error(exceptionConfig.getCode(),exceptionConfig.getMessage())
                .put("data",e.getMessage());
    }




    /**
     *
     * @param  e 业务逻辑抛出的异常
     * @return 统一返回包装后的结果
     */
    @ExceptionHandler({RuntimeException.class})
    public AjaxResult handleException(RuntimeException e) {
        Class<? extends Throwable> clazz = e.getClass();

        ExceptionMapper exceptionMapper = clazz.getAnnotation(ExceptionMapper.class);
        if (exceptionMapper != null){
            return AjaxResult.error(exceptionMapper.code(),exceptionMapper.msg());
        }
        return AjaxResult.error(exceptionConfig.getCode(),exceptionConfig.getMessage())
                .put("data",e.getMessage());
    }


}
