package com.insight.utils.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insight.utils.common.ContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author duxl
 * @date 2017年8月22日
 * @remark 常用Generator
 */
public final class Generator {
    private static final Logger logger = LoggerFactory.getLogger(Generator.class);
    private static final LockHandler LOCK = ContextHolder.getContext().getBean(LockHandler.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Map<String, Object> SET;

    static {
        String val = Redis.get("Config:GarbleSet");
        if (val == null || val.isEmpty()) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                list.add(flushLeft(i, 2));
            }

            Random random = new Random();
            SET = new HashMap<>();
            for (int i = 0; i < 100; i++) {
                int index = random.nextInt(100 - i);
                SET.put(flushLeft(i, 2), list.get(index));
                list.remove(index);
            }

            Redis.set("Config:GarbleSet", toJson());
        } else {
            SET = toMap(val);
        }
    }

    /**
     * 获取指定格式的加密编码
     *
     * @param format 编码格式,日期格式为yyyy-MM-dd的组合,流水号格式为#l,l为加密流水号位数
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
        String code = flushLeft(no, len);
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
     * 数字转固定长度字符串,左补零
     *
     * @param num 输入数字
     * @param len 字符串长度
     * @return 转换后的字符串
     */
    private static String flushLeft(int num, int len) {
        String format = "%0" + len + "d";

        return String.format(format, num);
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
        String low = String.valueOf(SET.get(str.substring(len - 2, len)));

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
            String date = getDateTime(format);
            if (date == null || date.isEmpty()) {
                continue;
            }

            str = str.replace(format, date);
        }

        return str;
    }

    /**
     * 获取当前日期时间
     *
     * @param pattern - 日期时间格式
     * @return 指定格式的日期时间
     */
    private static String getDateTime(String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime datetime = LocalDateTime.now();

            return datetime.format(formatter);
        } catch (Exception ex) {
            logger.error("发生异常: {}", ex.getMessage());
        }

        return null;
    }

    /**
     * 将bean转换成json
     *
     * @return json
     */
    private static String toJson() {
        try {
            return MAPPER.writeValueAsString(Generator.SET);
        } catch (IOException ex) {
            logger.error("序列化对象失败! {}", ex.getMessage());
            return null;
        }
    }

    /**
     * 将json字符串转换为HashMap
     *
     * @param json json
     * @return HashMap
     */
    @SuppressWarnings("unchecked")
    private static Map<String, Object> toMap(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            return MAPPER.readValue(json, HashMap.class);
        } catch (IOException ex) {
            logger.error("反序列化为Map失败! {}", ex.getMessage());
            return null;
        }
    }
}
