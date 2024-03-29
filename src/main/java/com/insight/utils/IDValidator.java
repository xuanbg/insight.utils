package com.insight.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author duxl
 * @date 2017/11/13
 * @remark <p>
 * 身份证合法性校验
 * </p>
 * <p>
 * <pre>
 * --15位身份证号码：第7、8位为出生年份(两位数)，第9、10位为出生月份，第11、12位代表出生日期，第15位代表性别，奇数为男，偶数为女。
 * --18位身份证号码：第7、8、9、10位为出生年份(四位数)，第11、第12位为出生月份，第13、14位代表出生日期，第17位代表性别，奇数为男，偶数为女。
 *    最后一位为校验位
 * </pre>
 */
public final class IDValidator {
    /**
     * <pre>
     * 省、直辖市代码表：
     *     11 : 北京  12 : 天津  13 : 河北       14 : 山西  15 : 内蒙古
     *     21 : 辽宁  22 : 吉林  23 : 黑龙江  31 : 上海  32 : 江苏
     *     33 : 浙江  34 : 安徽  35 : 福建       36 : 江西  37 : 山东
     *     41 : 河南  42 : 湖北  43 : 湖南       44 : 广东  45 : 广西      46 : 海南
     *     50 : 重庆  51 : 四川  52 : 贵州       53 : 云南  54 : 西藏
     *     61 : 陕西  62 : 甘肃  63 : 青海       64 : 宁夏  65 : 新疆
     *     71 : 台湾
     *     81 : 香港  82 : 澳门
     *     91 : 国外
     * </pre>
     */
    private static final String[] cityCode = {"11", "12", "13", "14", "15", "21",
            "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42",
            "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
            "63", "64", "65", "71", "81", "82", "91"};

    /**
     * 每位加权因子
     */
    private static final int[] power = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    /**
     * 验证所有的身份证的合法性
     *
     * @param idcard 身份证
     * @return 合法返回true，否则返回false
     */
    public static boolean isValidIdcard(String idcard) {
        if (idcard == null || "".equals(idcard)) {
            return false;
        }

        if (idcard.length() == 15) {
            return validate15Idcard(idcard);
        }

        return validate18Idcard(idcard);
    }

    /**
     * 判断18位身份证的合法性
     *
     * @param idcard 18位身份证号
     * @return 是否通过校验
     */
    public static boolean validate18Idcard(String idcard) {
        if (idcard == null) {
            return false;
        }

        if (idcard.length() != 18) {
            return false;
        }

        String idcard17 = idcard.substring(0, 17);
        if (isDigital(idcard17)) {
            return false;
        }

        // 校验省份
        String provinceid = idcard.substring(0, 2);
        if (checkProvinceid(provinceid)) {
            return false;
        }

        // 校验出生日期
        String birthday = idcard.substring(6, 14);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date birthDate = sdf.parse(birthday);
            String tmpDate = sdf.format(birthDate);
            if (!tmpDate.equals(birthday)) {
                return false;
            }
        } catch (ParseException e1) {
            return false;
        }

        // 获取第18位
        String idcard18Code = idcard.substring(17, 18);
        char[] c = idcard17.toCharArray();
        int[] bit = converCharToInt(c);
        int sum17 = getPowerSum(bit);

        // 将和值与11取模得到余数进行校验码判断
        String checkCode = getCheckCodeBySum(sum17);
        if (null == checkCode) {
            return false;
        }

        // 将身份证的第18位与算出来的校码进行匹配，不相等就为假
        return idcard18Code.equalsIgnoreCase(checkCode);
    }

    /**
     * 校验15位身份证
     *
     * @param idcard 15位身份证号
     * @return 是否通过校验
     */
    public static boolean validate15Idcard(String idcard) {
        if (idcard == null) {
            return false;
        }
        // 非15位为假
        if (idcard.length() != 15) {
            return false;
        }

        // 15全部为数字
        if (isDigital(idcard)) {
            return false;
        }

        // 校验省份
        String provinceid = idcard.substring(0, 2);
        if (checkProvinceid(provinceid)) {
            return false;
        }

        String birthday = idcard.substring(6, 12);
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        try {
            Date birthDate = sdf.parse(birthday);
            String tmpDate = sdf.format(birthDate);
            if (!tmpDate.equals(birthday)) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 将15位的身份证转成18位身份证
     *
     * @param idcard 15位身份证号
     * @return 18位身份证号
     */
    public static String convertIdcarBy15bit(String idcard) {
        if (idcard == null) {
            return null;
        }

        if (idcard.length() != 15) {
            return null;
        }

        if (isDigital(idcard)) {
            return null;
        }

        // 校验省份
        String provinceid = idcard.substring(0, 2);
        if (checkProvinceid(provinceid)) {
            return null;
        }

        String birthday = idcard.substring(6, 12);
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        Date birthdate;
        try {
            birthdate = sdf.parse(birthday);
            String tmpDate = sdf.format(birthdate);
            if (!tmpDate.equals(birthday)) {
                return null;
            }

        } catch (ParseException e1) {
            return null;
        }

        Calendar cday = Calendar.getInstance();
        cday.setTime(birthdate);
        String year = String.valueOf(cday.get(Calendar.YEAR));
        String idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);
        char[] c = idcard17.toCharArray();
        String checkCode;

        // 将字符数组转为整型数组
        int[] bit = converCharToInt(c);
        int sum17 = getPowerSum(bit);

        // 获取和值与11取模得到余数进行校验码
        checkCode = getCheckCodeBySum(sum17);

        // 获取不到校验位
        if (null == checkCode) {
            return null;
        }
        // 将前17位与第18位校验码拼接
        idcard17 += checkCode;

        return idcard17;
    }

    /**
     * 校验省份
     *
     * @param provinceid 省份ID
     * @return 合法返回TRUE，否则返回FALSE
     */
    private static boolean checkProvinceid(String provinceid) {
        for (String id : cityCode) {
            if (id.equals(provinceid)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 数字验证
     *
     * @param str 数字
     * @return 是否合法
     */
    private static boolean isDigital(String str) {
        return !str.matches("^[0-9]*$");
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     *
     * @param bit 校验码
     * @return 校验码
     */
    private static int getPowerSum(int[] bit) {
        int sum = 0;

        if (power.length != bit.length) {
            return sum;
        }

        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }

    /**
     * 将和值与11取模得到余数进行校验码判断
     *
     * @param sum17 校验码
     * @return 校验位
     */
    private static String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        switch (sum17 % 11) {
            case 10 -> checkCode = "2";
            case 9 -> checkCode = "3";
            case 8 -> checkCode = "4";
            case 7 -> checkCode = "5";
            case 6 -> checkCode = "6";
            case 5 -> checkCode = "7";
            case 4 -> checkCode = "8";
            case 3 -> checkCode = "9";
            case 2 -> checkCode = "x";
            case 1 -> checkCode = "0";
            case 0 -> checkCode = "1";
            default -> {
            }
        }
        return checkCode;
    }

    /**
     * 将字符数组转为整型数组
     *
     * @param c 字符数组
     * @return 整型数组
     */
    private static int[] converCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }
}
