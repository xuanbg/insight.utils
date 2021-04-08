package com.insight.utils;

import com.insight.utils.common.BusinessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    private static final Map<String, String> FORMAT_MAP;

    /**
     * 日期时间常用格式
     */
    private static final String TIME_PATTERN = "uuuu-MM-dd HH:mm:ss";

    /**
     * 默认日期格式化器
     */
    public static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd");

    /**
     * 默认时间格式化器
     */
    public static final DateTimeFormatter DEFAULT_TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);

    static {
        FORMAT_MAP = new HashMap<>(16);
        FORMAT_MAP.put("^\\d{2}/\\d{1,2}/\\d{1,2}/\\d{1,2}/\\d{1,2}/\\d{1,2}.*$", "yy/MM/dd/HH/mm/ss");
        FORMAT_MAP.put("^\\d{4}/\\d{1,2}/\\d{1,2}/\\d{1,2}/\\d{1,2}/\\d{1,2}.*$", "yyyy/MM/dd/HH/mm/ss");
        FORMAT_MAP.put("^\\d{2}/\\d{1,2}/\\d{1,2}/\\d{1,2}/\\d{1,2}$", "yy/MM/dd/HH/mm");
        FORMAT_MAP.put("^\\d{4}/\\d{1,2}/\\d{1,2}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd/HH/mm");
        FORMAT_MAP.put("^\\d{2}/\\d{1,2}/\\d{1,2}/\\d{1,2}$", "yy/MM/dd/HH");
        FORMAT_MAP.put("^\\d{4}/\\d{1,2}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd/HH");
        FORMAT_MAP.put("^\\d{2}/\\d{1,2}/\\d{1,2}$", "yy/MM/dd");
        FORMAT_MAP.put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
        FORMAT_MAP.put("^\\d{2}/\\d{1,2}$", "yy/MM");
        FORMAT_MAP.put("^\\d{4}/\\d{1,2}$", "yyyy/MM");
        FORMAT_MAP.put("^\\d{2}$", "yy");
        FORMAT_MAP.put("^\\d{4}$", "yyyy");
        FORMAT_MAP.put("^\\d{12}\\d{1,2}$", "yyyyMMddHHmmss");
        FORMAT_MAP.put("^\\d{10}\\d{1,2}$", "yyyyMMddHHmm");
        FORMAT_MAP.put("^\\d{8}\\d{1,2}$", "yyyyMMddHH");
        FORMAT_MAP.put("^\\d{6}\\d{1,2}$", "yyyyMMdd");
        FORMAT_MAP.put("^\\d{4}\\d{1,2}$", "yyyyMM");
    }

    /**
     * 构造方法
     */
    public DateTime() {
    }

    /**
     * 格式化当前日期
     *
     * @return uuuu-MM-dd格式的当前日期字符串
     */
    public static String formatCurrentDate() {
        return LocalDate.now().toString();
    }

    /**
     * 格式化当前时间
     *
     * @return uuuu-MM-dd HH:mm:ss格式的当前时间字符串
     */
    public static String formatCurrentTime() {
        return formatCurrentTime(TIME_PATTERN);
    }

    /**
     * 格式化当前时间
     *
     * @param pattern - 日期时间格式
     * @return 指定格式的当前时间字符串
     */
    public static String formatCurrentTime(String pattern) {
        return formatDateTime(LocalDateTime.now(), pattern);
    }

    /**
     * 格式化指定时间
     *
     * @param time    时间字符串
     * @param pattern 日期时间格式
     * @return 指定格式的时间字符串
     */
    public static String formatDateTime(LocalDateTime time, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return time.format(formatter);
    }

    /**
     * 格式化指定时间
     *
     * @param time 时间字符串
     * @return uuuu-MM-dd HH:mm:ss格式的时间字符串
     */
    public static String formatDateTime(String time) {
        LocalDateTime value = parseDateTime(time);
        return DEFAULT_TIME_FORMATTER.format(value);
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
     * 字符串转换LocalDate
     *
     * @param time 日期字符串(uuuu-MM-dd)
     * @return LocalDate
     */
    public static LocalDate parseDate(String time) {
        return parseDateTime(time).toLocalDate();
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
     * 字符串转换LocalDateTime
     *
     * @param time 时间字符串
     * @return LocalDateTime
     */
    public static LocalDateTime parseDateTime(String time) {
        String value = time.replaceAll("\\D+", "/");
        for (Map.Entry<String, String> entry : FORMAT_MAP.entrySet()) {
            if (Pattern.compile(entry.getKey()).matcher(value).matches()) {
                String format = entry.getValue();
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                try {
                    Date date = formatter.parse(value);
                    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                } catch (ParseException ex) {
                    throw new BusinessException("不合法的时间日期格式: " + ex.getMessage());
                }
            }
        }

        throw new BusinessException("不合法的时间日期格式: " + value);
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
}
