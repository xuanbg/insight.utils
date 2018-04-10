package com.insight.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

/**
 * @author duxl
 * @date 2017年8月22日
 * @remark 常用Generator
 */
public final class Generator {

    private Generator() {
    }

    /**
     * 生成uuid
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 生成指定长度的随机数字字符串
     *
     * @param length 长度
     * @return 数字字符串
     */
    public static String randomInt(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    /**
     * 生成指定长度的随机字符串
     *
     * @param length 长度
     * @return 随机字符串
     */
    public static String randomStr(int length) {
        return RandomStringUtils.random(length);
    }

    /**
     * 生成指定长度的字母和数字的随机组合字符串
     *
     * @param length 长度
     * @return 随机字母和数字的字符串组合
     */
    public static String randomAlphanumeric(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
