package com.czh.tool.czh.tool.service;

import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 文件上传
 *
 * @author czh
 */
@Component
public interface FileUploadService {

    String upload(Object... args) throws IOException;
}
