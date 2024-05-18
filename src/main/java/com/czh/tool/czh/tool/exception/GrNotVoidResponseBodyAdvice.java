package com.czh.tool.czh.tool.exception;


import com.czh.tool.czh.tool.config.ValidatedSuccessConfig;
import com.czh.tool.czh.tool.response.AjaxResult;
import com.czh.tool.czh.tool.response.ResponseFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 非空返回值的处理.
 *
 * @author <a href="mailto:943868899@qq.com">Yujie</a>
 * @version 0.1
 * @since 0.1
 */
@ControllerAdvice
@Order(value = 1000)
public class GrNotVoidResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private final Logger logger = LoggerFactory.getLogger(GrNotVoidResponseBodyAdvice.class);


    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private ObjectMapper objectMapper;
    /**
     * 路径过滤器
     */
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    /**
     * 只处理不返回void的，并且MappingJackson2HttpMessageConverter支持的类型.
     *
     * @param methodParameter 方法参数
     * @param clazz           处理器
     * @return 是否支持
     */
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> clazz) {
        Method method = methodParameter.getMethod();

        //method为空、返回值为void、非JSON，直接跳过
        if (Objects.isNull(method)
                || method.getReturnType().equals(Void.TYPE)){
            logger.debug("Graceful Response:method为空、返回值为void和Response类型、非JSON，跳过");
        return false;


    }
        //method为空、返回值为void、非JSON，直接跳过
        if (method.getReturnType().getName().equals("com.czh.tool.czh.tool.response.AjaxResult")){
            return false;
        }


        return true;
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
