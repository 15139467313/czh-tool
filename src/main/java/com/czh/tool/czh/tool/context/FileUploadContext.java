package com.czh.tool.czh.tool.context;

import com.czh.tool.czh.tool.annotation.type.FileUploadType;
import com.czh.tool.czh.tool.enums.FileUploadStrategy;
import com.czh.tool.czh.tool.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件上传策略模式
 *
 * @author czh
 */
@Component
public class FileUploadContext {
    private final Map<FileUploadStrategy, FileUploadService> fileUploadServiceMap = new ConcurrentHashMap<>();
    @Autowired
    private ApplicationContext applicationContext;

    // 无参构造方法
    public FileUploadContext() {
    }

    // 带ApplicationContext参数的构造方法
    public FileUploadContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(FileUploadType.class);
        for (Object bean : beans.values()) {
            FileUploadType loginType = bean.getClass().getAnnotation(FileUploadType.class);
            // 检查map中是否已存在该类型的服务，并且新的bean不是@Primary时跳过
            if (!fileUploadServiceMap.containsKey(loginType.value()) ||
                    bean.getClass().isAnnotationPresent(Primary.class)) {
                fileUploadServiceMap.put(loginType.value(), (FileUploadService) bean);
            }
        }

    }

    public String upload(Object... params) throws IOException {
        FileUploadService loginService = fileUploadServiceMap.get(params[0]);
        if (loginService == null) {
            throw new IllegalArgumentException("未知上传类型: " + params[0]);
        }
        return loginService.upload(params);
    }
}
