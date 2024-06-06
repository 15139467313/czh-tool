package com.czh.tool.czh.tool.context;

import com.czh.tool.czh.tool.annotation.type.FileDownType;
import com.czh.tool.czh.tool.annotation.type.FileUploadType;
import com.czh.tool.czh.tool.enums.FileStrategy;
import com.czh.tool.czh.tool.service.FileDownService;
import com.czh.tool.czh.tool.service.FileUploadService;
import com.czh.tool.czh.tool.service.impl.WordDownServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件上传策略模式
 *
 * @author czh
 */
@Component
public class FileDownContext {
    private final Map<FileStrategy, FileDownService> fileDownServiceMap = new ConcurrentHashMap<>();
    @Autowired
    private ApplicationContext applicationContext;

    // 无参构造方法
    public FileDownContext() {
    }

    // 带ApplicationContext参数的构造方法
    public FileDownContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(FileDownType.class);
        for (Object bean : beans.values()) {
            FileDownType fileDownType = bean.getClass().getAnnotation(FileDownType.class);
            // 检查map中是否已存在该类型的服务，并且新的bean不是@Primary时跳过
            if (!fileDownServiceMap.containsKey(fileDownType.value()) ||
                    bean.getClass().isAnnotationPresent(Primary.class)) {
                fileDownServiceMap.put(fileDownType.value(), (FileDownService) bean);
            }
        }

    }


    public void down(Object... params) throws IOException {
        FileStrategy fileStrategy= (FileStrategy) params[0];
        FileDownService fileDownService = fileDownServiceMap.get(fileStrategy);
        if (fileDownService == null) {
            throw new IllegalArgumentException("未知下载类型: " + params[0]);
        }
         fileDownService.down(params);
    }
}
