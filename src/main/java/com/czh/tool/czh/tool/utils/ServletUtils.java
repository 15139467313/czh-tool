package com.czh.tool.czh.tool.utils;

import com.czh.tool.czh.tool.constant.Constants;
import com.czh.tool.czh.tool.text.Convert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 客户端工具类
 *
 * 提供处理servlet请求和响应的各种工具方法。
 *
 */
public class ServletUtils {

    /**
     * 获取请求参数的值作为字符串。
     *
     * @param name 参数名
     * @return 参数值作为字符串
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取请求参数的值作为字符串，并提供默认值。
     *
     * @param name 参数名
     * @param defaultValue 默认值
     * @return 参数值作为字符串，如果参数不存在则返回默认值
     */
    public static String getParameter(String name, String defaultValue) {
        return Convert.toStr(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取请求参数的值作为整数。
     *
     * @param name 参数名
     * @return 参数值作为整数
     */
    public static Integer getParameterToInt(String name) {
        return Convert.toInt(getRequest().getParameter(name));
    }

    /**
     * 获取所有请求参数。
     *
     * @param request 请求对象{@link ServletRequest}
     * @return 参数的Map
     */
    public static Map<String, String[]> getParams(ServletRequest request) {
        final Map<String, String[]> map = request.getParameterMap();
        return Collections.unmodifiableMap(map);
    }

    /**
     * 获取所有请求参数并以键值对形式返回。
     *
     * @param request 请求对象{@link ServletRequest}
     * @return 参数的键值对Map
     */
    public static Map<String, String> getParamMap(ServletRequest request) {
        Map<String, String> params = new HashMap<>();
        for (Map.Entry<String, String[]> entry : getParams(request).entrySet()) {
            params.put(entry.getKey(), StringUtils.join(entry.getValue(), ","));
        }
        return params;
    }

    /**
     * 获取当前的HttpServletRequest对象。
     *
     * @return 当前的HttpServletRequest对象
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取当前的HttpServletResponse对象。
     *
     * @return 当前的HttpServletResponse对象
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取当前的HttpSession对象。
     *
     * @return 当前的HttpSession对象
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取当前的ServletRequestAttributes对象。
     *
     * @return 当前的ServletRequestAttributes对象
     */
    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 将字符串渲染到客户端。
     *
     * @param response 响应对象
     * @param string 待渲染的字符串
     */
    public static void renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否是Ajax异步请求。
     *
     * @param request 请求对象
     * @return 如果是Ajax异步请求返回true，否则返回false
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        if (accept != null && accept.contains("application/json")) {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.contains("XMLHttpRequest")) {
            return true;
        }

        String uri = request.getRequestURI();
        if (StringUtils.inStringIgnoreCase(uri, ".json", ".xml")) {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        return StringUtils.inStringIgnoreCase(ajax, "json", "xml");
    }

    /**
     * URL编码。
     *
     * @param str 待编码的内容
     * @return 编码后的内容
     */
    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, Constants.UTF8);
        } catch (UnsupportedEncodingException e) {
            return StringUtils.EMPTY;
        }
    }

    /**
     * URL解码。
     *
     * @param str 待解码的内容
     * @return 解码后的内容
     */
    public static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, Constants.UTF8);
        } catch (UnsupportedEncodingException e) {
            return StringUtils.EMPTY;
        }
    }
}
