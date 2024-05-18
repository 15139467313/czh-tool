package com.czh.tool.czh.tool.utils;

import java.util.UUID;

/**
 * @ClassNAME UUIDUtil
 * @Description TODO
 * @Author czh
 * @Date 2024/1/10 11:11
 * @Version 1.0
 */

public class UUIDUtil {
    public static String generateKey(){
        String substring = UUID.randomUUID().toString().replaceAll("-", "");
        return substring;
    }
}
