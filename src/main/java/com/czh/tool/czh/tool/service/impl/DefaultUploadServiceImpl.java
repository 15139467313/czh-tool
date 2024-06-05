package com.czh.tool.czh.tool.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.czh.tool.czh.tool.annotation.type.FileUploadType;
import com.czh.tool.czh.tool.config.FileUploadConfig;
import com.czh.tool.czh.tool.enums.FileUploadStrategy;
import com.czh.tool.czh.tool.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * 普通文件上传
 *
 * @author czh
 */
@Service
@Slf4j
@FileUploadType(FileUploadStrategy.DEFAULT)
public class DefaultUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileUploadConfig fileUploadPath;


    @Override
    public String upload(Object... args) throws IOException {
        MultipartFile file = (MultipartFile) args[1];
        if (file != null && !file.isEmpty()) {
            //获取文件原始名称
            String originalFilename = file.getOriginalFilename();
            //获取文件的类型
            String type = FileUtil.extName(originalFilename);
            log.info("文件类型是：" + type);
            //获取文件大小
            long size = file.getSize();
            String datePath = new SimpleDateFormat("yyyyMMdd").format(new Date());
            //获取文件
            File uploadParentFile = new File(fileUploadPath.getFileUploadPath() + "/" + datePath);
            //判断文件目录是否存在
            if (!uploadParentFile.exists()) {
                //如果不存在就创建文件夹
                uploadParentFile.mkdirs();
            }
            //定义一个文件唯一标识码（UUID）
            String uuid = UUID.randomUUID().toString();
            File uploadFile = new File(uploadParentFile + "/" + uuid + StrUtil.DOT + type);
            //将临时文件转存到指定磁盘位置
            file.transferTo(uploadFile);
            return "/"+fileUploadPath.getProjectName()+"/"+datePath + "/" + uuid + StrUtil.DOT + type;
        }
        return null;
    }
}
