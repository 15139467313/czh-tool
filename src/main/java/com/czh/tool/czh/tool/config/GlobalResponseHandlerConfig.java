package com.czh.tool.czh.tool.config;

/**
 * @ClassNAME GlobalResponseHandler
 * @Description TODO
 * @Author czh
 * @Date 2024/5/18 22:08
 * @Version 1.0
 */

import com.czh.tool.czh.tool.response.AjaxResult;
import com.czh.tool.czh.tool.response.ResponseFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import java.util.Objects;

@ControllerAdvice
@Order(value = 1000)
public class GlobalResponseHandlerConfig implements ResponseBodyAdvice<Object> {




    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Class clazz) {
        return Objects.requireNonNull(methodParameter.getMethod()).getReturnType().equals(Void.TYPE);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        // 将响应体包装成 ApiResponse 对象
        ValidatedSuccessConfig<Object> apiResponse = (ValidatedSuccessConfig<Object>) responseFactory.newSuccessInstance(body);

        // 如果返回值是 String 类型，将 ApiResponse 对象转换为 JSON 字符串
        if (body instanceof String) {
            try {
                return objectMapper.writeValueAsString(apiResponse);
            } catch (Exception e) {
                throw new RuntimeException("Error converting ApiResponse to JSON string", e);
            }
        }

        // 否则返回 ApiResponse 对象
        return apiResponse;

    }
}

