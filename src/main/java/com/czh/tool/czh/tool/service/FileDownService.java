package com.czh.tool.czh.tool.service;

import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 文件下载
 *
 * @author czh
 */
@Component
public interface FileDownService {

    void down(Object... args) throws IOException;
}
