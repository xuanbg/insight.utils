package com.insight.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author duxl
 * @date 2017年9月14日
 * @remark 基础帮助类
 */
public final class Util {

    /**
     * 获取文件字节数
     *
     * @param path 文件路径
     * @return 字节数
     */
    public static Long getFileSize(String path) {
        var file = new File(path);
        return file.length();
    }

    /**
     * 获取文件哈希值
     *
     * @param path 文件路径
     * @return 哈希值
     */
    public static String getFileHash(String path) throws IOException, NoSuchAlgorithmException {
        var md = MessageDigest.getInstance("MD5");

        int length;
        var buffer = new byte[8192];
        var fs = new FileInputStream(path);
        while ((length = fs.read(buffer)) != -1) {
            md.update(buffer, 0, length);
        }
        fs.close();

        var digest = md.digest();
        var builder = new StringBuilder();
        for (var b : digest) {
            builder.append(String.format("%02x", b));
        }

        return builder.toString();
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
     * 使用正则提取字符串
     *
     * @param regex  正则表达式
     * @param source 源字符串
     * @param group  分组
     * @return 符合正则的字符串
     */
    public static String getMatcher(String regex, String source, int group) {
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(source);

        return matcher.find() ? matcher.group(group) : null;
    }

    /**
     * 取最小值
     *
     * @param args 整数数组
     * @return 最小值
     */
    public static Integer getMin(Integer... args) {
        return Arrays.stream(args).min(Integer::compare).orElseThrow();
    }

    /**
     * 取最大值
     *
     * @param args 整数数组
     * @return 最大值
     */
    public static Integer getMax(Integer... args) {
        return Arrays.stream(args).max(Integer::compare).orElseThrow();
    }

    /**
     * 数组取最小值
     *
     * @param args 整数数组
     * @return 最小值
     */
    public static Integer getMin(int[] args) {
        return Arrays.stream(args).min().orElseThrow();
    }

    /**
     * 数组取最大值
     *
     * @param args 整数数组
     * @return 最大值
     */
    public static Integer getMax(int[] args) {
        return Arrays.stream(args).max().orElseThrow();
    }

    /**
     * 数值是否为0
     *
     * @param decimal 数值
     * @return 是否为0
     */
    public static boolean isZero(BigDecimal decimal) {
        return decimal == null || BigDecimal.ZERO.compareTo(decimal) == 0;
    }

    /**
     * 数值是否为正数
     *
     * @param decimal 数值
     * @return 是否为正数
     */
    public static boolean isPositive(BigDecimal decimal) {
        return decimal != null && BigDecimal.ZERO.compareTo(decimal) < 0;
    }

    /**
     * 数值是否为负数
     *
     * @param decimal 数值
     * @return 是否为负数
     */
    public static boolean isNegative(BigDecimal decimal) {
        return decimal != null && BigDecimal.ZERO.compareTo(decimal) > 0;
    }

    /**
     * 获取一个0-指定最大值的随机数
     *
     * @param max 最大值
     * @return 随机正整数
     */
    public static int getRandom(int max) {
        var random = Math.random() * (max + 1);
        return (int) Math.floor(random);
    }

    /**
     * 生成一个指定长度的纯数字组成的随机字符串
     *
     * @param length 生成字符串长度(1-8)
     * @return 随机字符串
     */
    public static String randomString(Integer length) {
        var base = "00000000";

        var max = (int) Math.pow(Double.parseDouble("10"), length.doubleValue());
        var random = getRandom(max);
        var r = String.valueOf(random);

        var len = r.length();
        return length.equals(len) ? r : base.substring(0, length - len) + r;
    }

    /**
     * 将对象转换成HashMap
     *
     * @param obj 源对象
     * @return HashMap
     */
    public static Map<String, String> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>(32);
        for (var field : obj.getClass().getDeclaredFields()) {
            var fieldName = field.getName();
            field.setAccessible(true);
            var val = field.get(obj);
            if (val == null) {
                continue;
            }

            var typeName = field.getType().getSimpleName();
            var value = "String".equals(typeName) ? (String) val : Json.toJson(val);
            map.put(fieldName, value);
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
        var format = "%0" + len + "d";

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
        var val = amount.multiply(BigDecimal.valueOf(100)).longValue();

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

        var isNegative = amount < 0;
        var cnAmount = new StringBuilder(isNegative ? "(负)" : "");
        var value = String.valueOf(Math.abs(amount));
        var length = value.length();

        var digital = "零壹贰叁肆伍陆柒捌玖";
        var position = "仟佰拾亿仟佰拾万仟佰拾元角分".substring(14 - length);
        var zeroCount = 0;
        for (var i = 0; i < length; i++) {
            var val = Integer.parseInt(value.substring(i, i + 1));
            var digVal = digital.substring(val, val + 1);
            var posVal = position.substring(i, i + 1);
            if (val > 0) {
                if (zeroCount > 0 && (length - i + 2) % 4 > 0) {
                    cnAmount.append("零");
                }

                zeroCount = 0;
            } else {
                digVal = "";
                var posIsEmpty = (length - i + 1) % 4 > 0 || (zeroCount > 2 && "万".equals(posVal));
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
     * 驼峰转下划线
     *
     * @param str 源字符串
     * @return 转换后字符串
     */
    public static String camelToUnderScore(String str) {
        var compile = Pattern.compile("[A-Z]");
        var matcher = compile.matcher(str);
        var sb = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }

        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param str 源字符串
     * @return 转换后字符串
     */
    public static String underScoreToCamel(String str) {
        str = str.toLowerCase();
        var compile = Pattern.compile("_[a-z]");
        var matcher = compile.matcher(str);
        var sb = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(0).toUpperCase().replace("_", ""));
        }

        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 16进制表示的字符串转换为字节数组
     *
     * @param hexString 16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStringToByteArray(String hexString) {
        var len = hexString.length();
        var bytes = new byte[len / 2];
        for (var i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character
                    .digit(hexString.charAt(i + 1), 16));
        }

        return bytes;
    }

    /**
     * byte[]数组转换为16进制的字符串
     *
     * @param data 要转换的字节数组
     * @return 转换后的结果
     */
    public static String byteArrayToHexString(byte[] data) {
        var sb = new StringBuilder(data.length * 2);
        for (var b : data) {
            var v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }

        return sb.toString().toUpperCase(Locale.getDefault());
    }

    /**
     * 逗号分割字符串转整型集合
     *
     * @param str 逗号分割字符串
     * @return 整型集合
     */
    public static List<Integer> toIntList(String str) {
        var list = str.split(",");
        return Arrays.stream(list).map(Integer::parseInt).collect(Collectors.toList());
    }

    /**
     * 逗号分割字符串转长整型集合
     *
     * @param str 逗号分割字符串
     * @return 长整型集合
     */
    public static List<Long> toLongList(String str) {
        var list = str.split(",");
        return Arrays.stream(list).map(Long::parseLong).collect(Collectors.toList());
    }

    /**
     * 逗号分割字符串转字符串集合
     *
     * @param str 逗号分割字符串
     * @return 字符串集合
     */
    public static List<String> toStringList(String str) {
        return toStringList(str, ",");
    }

    /**
     * 字符串转字符串集合
     *
     * @param str 分割字符
     * @return 字符串集合
     */
    public static List<String> toStringList(String str, String regex) {
        var list = str.split(regex);
        return Arrays.asList(list);
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
        var remaider = source.size() % n;
        var number = source.size() / n;
        var offset = 0;
        for (var i = 0; i < n; i++) {
            if (remaider > 0) {
                remaider--;
                offset++;
            }

            result.add(source.subList(i * number + offset, (i + 1) * number + offset));
        }

        return result;
    }

    /**
     * 比较两个集合是否不同
     *
     * @param source 源集合
     * @param target 目标集合
     * @param <T>    类型
     * @return 集合是否不同
     */
    public static <T> boolean isDifferent(List<T> source, List<T> target) {
        if (source == null && target == null) {
            return false;
        }

        if (source == null || target == null) {
            return true;
        }

        if (source.isEmpty() && target.isEmpty()) {
            return false;
        }

        if (source.size() != target.size()) {
            return true;
        }

        var elementType = source.get(0).getClass();
        List<T> s = Json.cloneList(source, elementType);
        List<T> t = Json.cloneList(target, elementType);

        return s.retainAll(t) || t.retainAll(s);
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
        var arr = content.split(s1);
        for (var a : arr) {
            var s = a.split("=");
            if (s.length < 2) {
                continue;
            }
            map.put(s[0], s[1]);
        }

        return map;
    }

    /**
     * 字符串是否不为空
     *
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty();
    }

    /**
     * 字符串是否为空
     *
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 集合是否不为空
     *
     * @param list 集合
     * @return 是否不为空
     */
    public static <T> boolean isNotEmpty(List<T> list) {
        return list != null && !list.isEmpty();
    }

    /**
     * 集合是否为空
     *
     * @param list 集合
     * @return 是否为空
     */
    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
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
