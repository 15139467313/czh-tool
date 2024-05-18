package com.czh.tool.czh.tool.utils;

import java.util.UUID;

/**
 * uuid生成
 *
 * @author czh
 * @since 1.0
 */
public class UUIDUtil {

    /**
     * 生成唯一键
     *
     * @return 去掉“-”后的UUID字符串
     */
    public static String generateKey() {
        // 生成一个UUID，并去掉其中的“-”字符
        String substring = UUID.randomUUID().toString().replaceAll("-", "");
        return substring;
    }
}
