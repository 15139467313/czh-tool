package com.czh.tool.czh.tool.service.impl;

import com.czh.tool.czh.tool.annotation.type.FileUploadType;
import com.czh.tool.czh.tool.config.FileUploadConfig;
import com.czh.tool.czh.tool.enums.FileStrategy;
import com.czh.tool.czh.tool.service.FileDownService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 普通文件下载
 *
 * @author czh
 */
@Service
@Slf4j

public class DefaultDownServiceImpl implements FileDownService {




    @Override
    public void down(Object... args) throws IOException {

    }
}
