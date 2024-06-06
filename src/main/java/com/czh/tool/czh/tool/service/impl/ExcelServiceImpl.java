package com.czh.tool.czh.tool.service.impl;

import com.czh.tool.czh.tool.annotation.FileDown;
import com.czh.tool.czh.tool.annotation.type.FileDownType;
import com.czh.tool.czh.tool.config.FileDownConfig;
import com.czh.tool.czh.tool.enums.FileStrategy;
import com.czh.tool.czh.tool.service.FileDownService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * pdf文件下载
 *
 * @author czh
 */
@Service
@Slf4j
@FileDownType(FileStrategy.EXCEL)
public class ExcelServiceImpl implements FileDownService {

    @Autowired
    private FileDownConfig fileDownConfig;


    @Override
    public void down(Object... args) throws IOException {
        try {
            HttpServletResponse response = (HttpServletResponse) args[2];
            FileDown fileDown = (FileDown) args[1];
            FileInputStream fis = new FileInputStream(fileDownConfig.getFileDownPath()+"/"+fileDown.fileName());
            ServletOutputStream out = response.getOutputStream();
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileDown.fileName(), "UTF-8"));
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fis.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
