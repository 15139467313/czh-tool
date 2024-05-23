package com.czh.tool.czhtool;

import com.czh.tool.czh.tool.CzhToolApplication;
import com.czh.tool.czh.tool.aspectj.SystemLogAspect;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CzhToolApplication.class)
class CzhToolApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(SystemLogAspect.class);
    @Test
    void contextLoads() {
        log.info("111");
    }

}
