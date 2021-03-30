package com.insight.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author 宣炳刚
 * @date 2017年8月23日
 * @remark 时间日期帮助类
 */
public final class DateTime {

    /**
     * 日期格式字典
     */
    private static final Map<String, String> FORMAT_MAP = new HashMap<>(16);

    /**
     * 日期常用格式
     */
    static String[] datePatterns = {"yyyy-MM-dd", "yyyyMMdd"};

    /**
     * 日期时间常用格式
     */
    static String[] dateTimePatterns = {"yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"};

    /**
     * 构造方法
     */
    public DateTime() {
    }

    /**
     * 获取与当天差若干天的日期
     *
     * @param days 天数
     * @return LocalDate
     */
    public static LocalDate getDate(long days) {
        return LocalDate.now().plusDays(days);
    }

    /**
     * 获取与当前时间差若干秒的时间
     *
     * @param seconds 秒数
     * @return LocalDateTime
     */
    public static LocalDateTime getTime(long seconds) {
        return LocalDateTime.now().plusSeconds(seconds);
    }

    /**
     * 格式化当前日期
     *
     * @return yyyy-MM-dd
     */
    public static String formatCurrentDate() {
        return LocalDate.now().toString();
    }

    /**
     * 格式化日期时间
     *
     * @param time 时间
     * @param pattern - 日期时间格式
     * @return 指定格式的日期时间
     */
    public static String formatDateTime(LocalDateTime time, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return time.format(formatter);
    }

    /**
     * 格式化当前日期时间
     *
     * @param pattern - 日期时间格式
     * @return 指定格式的日期时间
     */
    public static String formatCurrentTime(String pattern) {
        return formatDateTime(LocalDateTime.now(), pattern);
    }

    /**
     * 格式化当前日期时间(yyyy-MM-dd HH:mm:ss)
     *
     * @return 固定格式的日期时间
     */
    public static String formatCurrentTime() {
        return formatCurrentTime(dateTimePatterns[0]);
    }

    /**
     * 获取当前日期是星期几
     *
     * @return String - 返回星期几
     */
    public static Integer getWeek() {
        return LocalDate.now().getDayOfWeek().getValue();
    }

    /**
     * 计算某个日期是星期几
     *
     * @param date 日期
     * @return 1-7，7为星期天
     */
    public static Integer calcWeek(LocalDate date) {
        return date.getDayOfWeek().getValue();
    }

    /**
     * 获取当前月天数
     *
     * @return 天数
     */
    public static long getCurrentMonthDays() {
        LocalDate one = LocalDate.now().withDayOfMonth(1);
        return one.plusMonths(1).toEpochDay() - one.toEpochDay();
    }

    /**
     * 获取时间差天数
     *
     * @param startDate 开始日期
     * @param endDate   截止日期
     * @return 天数
     */
    public static long getDiffDays(LocalDate startDate, LocalDate endDate) {
        return endDate.toEpochDay() - startDate.toEpochDay();
    }

    /**
     * 计算时间差(秒)
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 秒数
     */
    public static long getDifference(LocalDateTime start, LocalDateTime end) {
        return end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC);
    }

    /**
     * 计算当前时间到某一时间的秒数
     *
     * @param endDate 截止时间
     * @return 秒数
     */
    public static Long getRemainSeconds(LocalDateTime endDate) {
        return getDifference(LocalDateTime.now(), endDate);
    }

    /**
     * 格式化日期时间字符串为yyyy-MM-dd HH-mm-ss格式
     *
     * @param date 输入的日期时间字符串
     * @return 格式化为yyyy-MM-dd HH-mm-ss格式的字符串
     */
    public static String dateFormat(String date) {
        if (FORMAT_MAP.isEmpty()) {
            FORMAT_MAP.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$", "yyyy-MM-dd-HH-mm-ss");
            FORMAT_MAP.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yyyy-MM-dd-HH-mm");
            FORMAT_MAP.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yyyy-MM-dd-HH");
            FORMAT_MAP.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}$", "yyyy-MM-dd");
            FORMAT_MAP.put("^\\d{4}\\D+\\d{1,2}$", "yyyy-MM");
            FORMAT_MAP.put("^\\d{4}$", "yyyy");
            FORMAT_MAP.put("^\\d{14}$", "yyyyMMddHHmmss");
            FORMAT_MAP.put("^\\d{12}$", "yyyyMMddHHmm");
            FORMAT_MAP.put("^\\d{10}$", "yyyyMMddHH");
            FORMAT_MAP.put("^\\d{8}$", "yyyyMMdd");
            FORMAT_MAP.put("^\\d{6}$", "yyyyMM");
            FORMAT_MAP.put("^\\d{2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yy-MM-dd");
            FORMAT_MAP.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}$", "dd-MM-yyyy");
        }

        DateTimeFormatter formatter = null;
        for (String key : FORMAT_MAP.keySet()) {
            if (Pattern.compile(key).matcher(date).matches()) {
                formatter = DateTimeFormatter.ofPattern(FORMAT_MAP.get(key));
                break;
            }
        }

        if (formatter == null) {
            return null;
        }

        String result = date.replaceAll("\\D+", "-");
        LocalDateTime dateValue = LocalDateTime.parse(result, formatter);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return formatter.format(dateValue);
    }
}
