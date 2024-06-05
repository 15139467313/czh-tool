package com.czh.tool.czh.tool.controller;

import com.czh.tool.czh.tool.annotation.FileUpload;
import com.czh.tool.czh.tool.enums.FileUploadStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class TestController {
    @PostMapping("/upload")
    @FileUpload()
    public Object test(@RequestParam("file") MultipartFile file){
        return "上传成功";
    }

    @GetMapping("/test")
    public String test1(){
        return "11";
    }
}
