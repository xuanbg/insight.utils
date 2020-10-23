package com.insight.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author duxl
 * @date 2017年9月14日
 * @remark 基础帮助类
 */
public final class Util {
    private static final Log logger = LogFactory.getLog(Util.class);

    /**
     * 生成uuid
     *
     * @return uuid
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成一个指定长度的纯数字组成的随机字符串
     *
     * @param length 生成字符串长度(1-8)
     * @return 随机字符串
     */
    public static String randomString(Integer length) {
        Random random = new Random();
        String base = "00000000";

        double max = Math.pow(Double.parseDouble("10"), length.doubleValue());
        String r = String.valueOf(random.nextInt((int) max));

        int len = r.length();
        return length.equals(len) ? r : base.substring(0, length - len) + r;
    }

    /**
     * 将对象转换成HashMap
     *
     * @param obj 源对象
     * @return HashMap
     */
    public static Map<String, String> objectToMap(Object obj) {
        Map<String, String> map = new HashMap<>(32);
        for (Field field : obj.getClass().getDeclaredFields()) {
            String fieldName = field.getName();
            try {
                field.setAccessible(true);
                Object val = field.get(obj);
                if (val == null) {
                    continue;
                }

                String typeName = field.getType().getSimpleName();
                String value = "String".equals(typeName) ? (String) val : Json.toJson(val);
                map.put(fieldName, value);
            } catch (IllegalAccessException ex) {
                logger.error("字段读取失败: " + ex.getMessage());
            }
        }

        return map;
    }

    /**
     * 数值金额转换为中文大写金额
     *
     * @param amount 金额(两位小数)
     * @return 中文大写金额
     */
    public static String convertAmountToCn(BigDecimal amount) {
        return convertAmountToCn(amount, 0);
    }

    /**
     * 数字转固定长度字符串,左补零
     *
     * @param num 输入数字
     * @param len 字符串长度
     * @return 转换后的字符串
     */
    public static String flushLeft(int num, int len) {
        String format = "%0" + len + "d";

        return String.format(format, num);
    }

    /**
     * 数值金额转换为中文大写金额
     *
     * @param amount 金额(两位小数)
     * @param type   补整类型,0:到角补整;1:到元补整
     * @return 中文大写金额
     */
    public static String convertAmountToCn(BigDecimal amount, int type) {
        long val = amount.multiply(BigDecimal.valueOf(100)).longValue();

        return convertAmountToCn(val, type);
    }

    /**
     * 数值金额转换为中文大写金额
     *
     * @param amount 金额(分)
     * @return 中文大写金额
     */
    public static String convertAmountToCn(long amount) {
        return convertAmountToCn(amount, 0);
    }

    /**
     * 数值金额转换为中文大写金额
     *
     * @param amount 金额(分)
     * @param type   补整类型,0:到角补整;1:到元补整
     * @return 中文大写金额
     */
    public static String convertAmountToCn(long amount, int type) {
        if (amount == 0) {
            return "零元整";
        }

        if (amount > Long.parseLong("99999999999999")) {
            return "不支持万亿及更高金额";
        }

        boolean isNegative = amount < 0;
        StringBuilder cnAmount = new StringBuilder(isNegative ? "(负)" : "");
        String value = String.valueOf(Math.abs(amount));
        int length = value.length();

        String digital = "零壹贰叁肆伍陆柒捌玖";
        String position = "仟佰拾亿仟佰拾万仟佰拾元角分".substring(14 - length);
        int zeroCount = 0;
        for (int i = 0; i < length; i++) {
            int val = Integer.parseInt(value.substring(i, i + 1));
            String digVal = digital.substring(val, val + 1);
            String posVal = position.substring(i, i + 1);
            if (val > 0) {
                if (zeroCount > 0 && (length - i + 2) % 4 > 0) {
                    cnAmount.append("零");
                }

                zeroCount = 0;
            } else {
                digVal = "";
                boolean posIsEmpty = (length - i + 1) % 4 > 0 || (zeroCount > 2 && "万".equals(posVal));
                if (posIsEmpty) {
                    posVal = "";
                }

                zeroCount++;
            }

            cnAmount.append(digVal).append(posVal);
        }

        if (zeroCount > type) {
            cnAmount.append("整");
        }

        return cnAmount.toString();
    }

    /**
     * 分割list
     *
     * @param source 原list
     * @param n      每个list size
     * @param <T>    类型
     * @return List<List>
     */
    public static <T> List<List<T>> splitList(List<T> source, Integer n) {
        List<List<T>> result = new ArrayList<>();
        int remaider = source.size() % n;
        int number = source.size() / n;
        int offset = 0;
        for (int i = 0; i < n; i++) {
            if (remaider > 0) {
                remaider--;
                offset++;
            }

            result.add(source.subList(i * number + offset, (i + 1) * number + offset));
        }

        return result;
    }

    /**
     * 将一个fix字符串转换成map
     *
     * @param content 字符串，比如x1=a&x2=b
     * @param s1      词组分隔符
     * @return map
     */
    public static Map<String, String> fix(String content, String s1) {
        Map<String, String> map = new HashMap<>(16);
        String[] arr = content.split(s1);
        for (String a : arr) {
            String[] s = a.split("=");
            if (s.length < 2) {
                continue;
            }
            map.put(s[0], s[1]);
        }

        return map;
    }

    /**
     * 字符串是否为空
     *
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty();
    }

    /**
     * 生成Map
     *
     * @param key   键
     * @param value 值
     * @return Map
     */
    public static Map<String, Object> generateMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>(16);
        map.put(key, value);

        return map;
    }

    /**
     * md5 加密
     *
     * @param key 字符串
     * @return 密文
     */
    public static String md5(String key) {
        return DigestUtils.md5Hex(key);
    }

    /**
     * sha 散列
     *
     * @param key 字符串
     * @return hash散列
     */
    public static String sha(String key) {
        return DigestUtils.sha256Hex(key);
    }

    /**
     * sha 散列
     *
     * @param key 字符串
     * @return hash散列
     */
    public static String sha1(String key) {
        return DigestUtils.sha1Hex(key);
    }
}
