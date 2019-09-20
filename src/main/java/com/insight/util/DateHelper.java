package com.insight.util;


import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author duxl
 * @date 2017年8月23日
 * @remark 时间日期帮助类，常用方法另参考apache DateUtils。
 */
public final class DateHelper {

    /**
     * 日期格式字典
     */
    private static Map<String, String> formatMap = new HashMap<>(16);

    /**
     * 日期常用格式
     */
    static String[] datePatterns = {"yyyy-MM-dd", "yyyyMMdd"};
    /**
     * 日期时间常用格式
     */
    static String[] dateTimePatterns = {"yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"};

    private DateHelper() {
    }

    /**
     * 获取当前日期
     *
     * @return yyyy-MM-dd
     */
    public static String getDate() {
        LocalDate localDate = LocalDate.now();

        return localDate.toString();
    }

    /**
     * 获取当前日期时间
     *
     * @return yyyyMMddHHmmss
     */
    public static String getDateTime() {
        return getDateTime(dateTimePatterns[0]);
    }


    /**
     * 获取当前日期时间
     *
     * @param pattern - 日期时间格式
     * @return 指定格式的日期时间
     */
    public static String getDateTime(String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime datetime = LocalDateTime.now();

            return datetime.format(formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 转换Str为Date
     *
     * @param dateStr 日期字符串
     * @return 日期
     */
    public static Date parseDate(String dateStr) {
        try {
            return DateUtils.parseDate(dateStr, datePatterns);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 转换Str为DateTme
     *
     * @param datetimeStr 日期时间字符串
     * @return 日期
     */
    public static Date parseDateTime(String datetimeStr) {
        try {
            return DateUtils.parseDate(datetimeStr, dateTimePatterns);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 格式化指定日期
     *
     * @param date 日期
     * @return yyyy-MM-dd
     */
    public static String formatDate(Date date) {
        return formatDate(date, datePatterns[0]);
    }

    /**
     * 格式化指定日期
     *
     * @param date    日期
     * @param pattern 格式
     * @return yyyy-MM-dd
     */
    public static String formatDate(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 判断给定的字符串是否是给定格式
     *
     * @param dateStr 日期字符串
     * @param pattern 日期格式
     * @return 是否是给定格式
     */
    public static Boolean validateFormat(String dateStr, String pattern) {
        try {
            DateUtils.parseDate(dateStr, pattern);

            return true;
        } catch (Exception ignored) {
        }

        return false;
    }

    /**
     * 获取当前日期是星期几
     *
     * @return String - 返回星期几
     */
    public static Integer getWeek() {
        Date date = new Date();

        return calcWeek(date);
    }

    /**
     * 获取指定日期是星期几
     *
     * @param dateStr 日期字符串
     * @return - 返回当前星期几
     */
    public static Integer getWeek(String dateStr) {
        try {
            Date date = DateUtils.parseDate(dateStr, datePatterns);

            return calcWeek(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * 计算某个日期是星期几
     *
     * @param date 日期
     * @return 0-6，0为星期天
     */
    public static Integer calcWeek(Date date) {
        int posOfWeek;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        posOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        posOfWeek--;

        return posOfWeek;
    }

    /**
     * 获取当月第一天
     *
     * @return 日期
     */
    public static String getMonthFirstDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);

        return DateFormatUtils.format(cal, datePatterns[0]);
    }

    /**
     * 获取当月最后一天
     *
     * @return 日期
     */
    public static String getMonthLastDay() {
        Calendar cal = Calendar.getInstance();
        Calendar f = (Calendar) cal.clone();
        f.clear();
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);

        return DateFormatUtils.format(cal, datePatterns[0]);
    }

    /**
     * 获取当前月天数
     *
     * @return 天数
     */
    public static int getCurrentMonthDays() {
        Calendar cal = new GregorianCalendar();

        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取时间差天数
     *
     * @param startDate 开始日期
     * @param endDate   截止日期
     * @return 天数
     */
    public static Integer getDiffDays(Date startDate, Date endDate) {
        long ei = getDiffSeconds(startDate, endDate);

        return (int) (ei / (1000 * 60 * 60 * 24));
    }

    /**
     * 获取时间差秒
     *
     * @param startDate 开始时间
     * @param endDate   截止数据
     * @return 秒数
     */
    public static Long getDiffSeconds(Date startDate, Date endDate) {
        if (startDate.after(endDate)) {
            Date cal = startDate;
            startDate = endDate;
            endDate = cal;
        }
        long sl = startDate.getTime();
        long el = endDate.getTime();

        return el - sl;
    }

    /**
     * 计算当前时间到某一时间的秒数
     *
     * @param endDate 截止时间
     * @return 秒数
     */
    public static Long getRemainSeconds(Date endDate) {
        return getRemainSeconds(endDate.getTime());
    }

    /**
     * 计算当前时间到某一时间的秒数
     *
     * @param endTime 截止时间戳
     * @return 秒数
     */
    public static Long getRemainSeconds(Long endTime) {
        return getRemainMilliSeconds(endTime) / 1000;
    }

    /**
     * 计算当前时间到某一时间的毫秒数
     *
     * @param endDate 截止时间
     * @return 毫秒数
     */
    public static Long getRemainMilliSeconds(Date endDate) {
        return getRemainMilliSeconds(endDate.getTime());
    }

    /**
     * 计算当前时间到某一时间的毫秒数
     *
     * @param endTime 截止时间戳
     * @return 毫秒数
     */
    public static Long getRemainMilliSeconds(Long endTime) {
        long currentTime = System.currentTimeMillis();
        if (currentTime >= endTime) {
            return 0L;
        }

        return endTime - currentTime;
    }

    /**
     * 格式化日期时间字符串为yyyy-MM-dd HH-mm-ss格式
     *
     * @param date 输入的日期时间字符串
     * @return 格式化为yyyy-MM-dd HH-mm-ss格式的字符串
     */
    public static String dateFormat(String date) {
        if (formatMap.isEmpty()) {
            formatMap.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$", "yyyy-MM-dd-HH-mm-ss");
            formatMap.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yyyy-MM-dd-HH-mm");
            formatMap.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yyyy-MM-dd-HH");
            formatMap.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}$", "yyyy-MM-dd");
            formatMap.put("^\\d{4}\\D+\\d{1,2}$", "yyyy-MM");
            formatMap.put("^\\d{4}$", "yyyy");
            formatMap.put("^\\d{14}$", "yyyyMMddHHmmss");
            formatMap.put("^\\d{12}$", "yyyyMMddHHmm");
            formatMap.put("^\\d{10}$", "yyyyMMddHH");
            formatMap.put("^\\d{8}$", "yyyyMMdd");
            formatMap.put("^\\d{6}$", "yyyyMM");
            formatMap.put("^\\d{2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yy-MM-dd");
            formatMap.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}$", "dd-MM-yyyy");
        }

        DateTimeFormatter formatter = null;
        for (String key : formatMap.keySet()) {
            if (Pattern.compile(key).matcher(date).matches()) {
                formatter = DateTimeFormatter.ofPattern(formatMap.get(key));
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
