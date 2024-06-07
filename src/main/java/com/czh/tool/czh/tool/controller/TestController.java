package com.czh.tool.czh.tool.controller;

import com.czh.tool.czh.tool.annotation.FileDown;
import com.czh.tool.czh.tool.annotation.FileUpload;
import com.czh.tool.czh.tool.annotation.Prevent;
import com.czh.tool.czh.tool.enums.FileStrategy;
import com.czh.tool.czh.tool.exception.CzhToolResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


@RestController
public class TestController {
    @PostMapping("/upload")
    @FileUpload()
    public Object test(@RequestParam("file") MultipartFile file){
        return "上传成功";
    }

    @GetMapping("/downWord")
    @FileDown(fileName = "test.doc",strategy = FileStrategy.WORD)
    public void downWord(HttpServletResponse response){
    }

    @GetMapping("/downPdf")
    @FileDown(fileName = "test.pdf",strategy = FileStrategy.PDF,checkSee = false)
    public void downPdf(HttpServletResponse response){
    }

    @GetMapping("/downExcel")
    @FileDown(fileName = "test.xlsx",strategy = FileStrategy.EXCEL)
    public void downExcel(HttpServletResponse response){
    }

    @GetMapping("/test")
//    @Prevent(value = "2000")
    public void test1(){
        CzhToolResponse.raiseException(501,"测试异常");
    }
}
