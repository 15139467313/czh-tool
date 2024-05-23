package com.czh.tool.czh.tool;

import com.czh.tool.czh.tool.aspectj.SystemLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication()
public class CzhToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(CzhToolApplication.class, args);
    }

}
