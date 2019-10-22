package com.insight.util;

import com.insight.util.common.ApplicationContextHolder;
import com.insight.util.common.LockHandler;
import com.insight.util.pojo.LockParam;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

/**
 * @author duxl
 * @date 2017年8月22日
 * @remark 常用Generator
 */
public final class Generator {
    private static final LockHandler LOCK = ApplicationContextHolder.getContext().getBean(LockHandler.class);
    private static Map<String, Object> set;

    static  {
        String val = Redis.get("Config:GarbleSet");
        if (val == null || val.isEmpty()) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                list.add(Util.flushLeft(i, 2));
            }

            Random random = new Random();
            set = new HashMap<>();
            for (int i = 0; i < 100; i++) {
                int index = random.nextInt(100 - i);
                set.put(Util.flushLeft(i, 2), list.get(index));
                list.remove(index);
            }

            Redis.set("Config:GarbleSet", Json.toJson(set));
        } else {
            set = Json.toMap(val);
        }
    }

    /**
     * 生成uuid
     *
     * @return uuid
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

    /**
     * 获取指定格式的编码
     *
     * @param format 编码格式,日期格式为yyyy-MM-dd的组合,流水号格式为#l,l为流水号位数,前补0
     * @param group  分组格式
     * @return 编码
     */
    public static String newCode(String format, String group) {
        return newCode(format, group, true);
    }

    /**
     * 获取指定格式的编码
     *
     * @param format    编码格式,日期格式为yyyy-MM-dd的组合,流水号格式为#l,l为流水号位数,前补0
     * @param group     分组格式
     * @param isEncrypt 是否加密流水号
     * @return 编码
     */
    public static String newCode(String format, String group, Boolean isEncrypt) {
        int index = format.indexOf("#");
        if (index < 0) {
            return "编码格式不正确！需要有流水号段，例如：#4";
        }

        String length = format.substring(index + 1, index + 2);
        int len = Integer.parseInt(length);
        if (len < 2 || len > 8) {
            return "编码格式不正确！流水号只允许2-8位";
        }

        // 格式化日期
        format = replace(format);
        group = replace(group);

        // 获取分布式锁
        LockParam param = new LockParam(group);
        if (!LOCK.tryLock(param)) {
            return null;
        }

        // 获取流水号,如流水号不存在,则生成流水号(加密方式初始为随机数,非加密方式初始为1)
        int no;
        String key = "CodeGroup:" + group;
        String val = Redis.get(key);
        if (val == null || val.isEmpty()) {
            if (isEncrypt) {
                int max = (int) Math.pow(10, len);
                Random random = new Random();
                no = random.nextInt(max);
            } else {
                no = 1;
            }
        } else {
            no = (Integer.parseInt(val) + 1);
        }

        // 更新流水号并释放锁
        Redis.set(key, String.valueOf(no));
        LOCK.releaseLock(param);

        // 格式化流水号
        String code = Util.flushLeft(no, len);
        int l = code.length();
        if (l > len) {
            code = code.substring(l - len, l);
        }

        // 如需加密流水号,进行位数减一次迭代加密流水号
        int i = len - 1;
        while (isEncrypt && i > 0) {
            i = i - 1;
            code = garble(code);
        }

        return format.replace("#" + len, code);
    }

    /**
     * 字符串混淆
     *
     * @param str 输入字符串
     * @return 混淆后字符串
     */
    private static String garble(String str) {
        int len = str.length();
        String first = len > 2 ? str.substring(0, 1) : "";
        String high = len > 3 ? str.substring(1, len - 2) : "";
        String low = String.valueOf(set.get(str.substring(len - 2, len)));

        return high + low + first;
    }

    /**
     * 日期替换
     *
     * @param str 原始字符串
     * @return 替换后的字符串
     */
    private static String replace(String str) {
        String[] array = {"yyyy", "yy", "MM", "dd"};
        for (String format : array) {
            String date = DateHelper.getDateTime(format);
            if (date == null || date.isEmpty()) {
                continue;
            }

            str = str.replace(format, date);
        }

        return str;
    }
}
