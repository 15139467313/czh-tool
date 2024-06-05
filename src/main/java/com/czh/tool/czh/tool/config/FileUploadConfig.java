package com.czh.tool.czh.tool.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 *
 * @author czh
 * @since 1.0
 */
@ConfigurationProperties(prefix = "czh.tool.file.upload")
@Component
public class FileUploadConfig implements Serializable {

    private String fileUploadPath;

    private String projectName;

    public String getFileUploadPath() {
        return fileUploadPath;
    }
    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }

    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
