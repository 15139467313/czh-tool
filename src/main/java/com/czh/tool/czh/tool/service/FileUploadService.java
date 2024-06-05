package com.czh.tool.czh.tool.service;

import com.czh.tool.czh.tool.enums.FileUploadStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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
