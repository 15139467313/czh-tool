package com.czh.tool.czh.tool.exception;

import com.czh.tool.czh.tool.annotation.ExceptionMapper;

/**
 * 这是一个文件异常类。
 * @author czh
 * @since 1.0
 */
@ExceptionMapper(code = 500,msg = "请输入HttpServletResponse必要参数")
public class FileException extends RuntimeException{
}
