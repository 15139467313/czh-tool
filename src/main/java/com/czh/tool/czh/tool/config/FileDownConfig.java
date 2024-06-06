package com.czh.tool.czh.tool.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 *
 * @author czh
 * @since 1.0
 */
@ConfigurationProperties(prefix = "czh.tool.file.down")
@Component
public class FileDownConfig implements Serializable {

    private String fileDownPath;

    private String projectName;

    public String getFileDownPath() {
        return fileDownPath;
    }

    public void setFileDownPath(String fileDownPath) {
        this.fileDownPath = fileDownPath;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
