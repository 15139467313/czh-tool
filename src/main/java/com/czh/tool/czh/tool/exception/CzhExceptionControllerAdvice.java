package com.czh.tool.czh.tool.exception;



import com.czh.tool.czh.tool.config.ValidatedExceptionConfig;
import com.czh.tool.czh.tool.response.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:  集中处理所有异常
 * @Created: with IntelliJ IDEA.
 * @author: czh
 * @createTime: 2020-05-27 17:14
 **/

@Slf4j
@RestControllerAdvice()
public abstract class CzhExceptionControllerAdvice {

    @Autowired
    private ValidatedExceptionConfig validatedExceptionConfig;

    /**
     * 参数非法（效验参数）异常 MethodArgumentNotValidException
     * @param e
     * @return
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
     * @param e
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AjaxResult handleValidException(ConstraintViolationException e) {
        log.error("数据效验出现问题{},异常类型{}",e.getMessage(),e.getClass());
       e.getConstraintViolations();
        return AjaxResult.error(validatedExceptionConfig.getCode(),validatedExceptionConfig.getMessage())
                .put("data",e.getMessage());
    }





}
