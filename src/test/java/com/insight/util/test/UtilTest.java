package com.insight.util.test;

import com.insight.util.Util;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * code by stock<chonglei>
 *
 * @author chenleijun
 * @Date 2017/12/8
 * @remark 基础帮助类
 */
public class UtilTest {

    @Test
    public void testConvertAmountToCn() {
        System.out.println("1.00元:" + Util.convertAmountToCn(100));
        System.out.println("-1.10元:" + Util.convertAmountToCn(-110, 1));
        System.out.println("1.01元:" + Util.convertAmountToCn(101));
        System.out.println("10.00元:" + Util.convertAmountToCn(1000));
        System.out.println("10.01元:" + Util.convertAmountToCn(1001));
        System.out.println("100.00元:" + Util.convertAmountToCn(10000));
        System.out.println("100.10元:" + Util.convertAmountToCn(10010));
        System.out.println("101.10元:" + Util.convertAmountToCn(10110));
        System.out.println("101.11元:" + Util.convertAmountToCn(10111));
        System.out.println("1,001.11元:" + Util.convertAmountToCn(100111));
        System.out.println("1,000.01元:" + Util.convertAmountToCn(100001));
        System.out.println("10,000.01元:" + Util.convertAmountToCn(1000001));
        System.out.println("11,000.01元:" + Util.convertAmountToCn(1100001));
        System.out.println("10,100.01元:" + Util.convertAmountToCn(1010001));
        System.out.println("10,000.00元:" + Util.convertAmountToCn(1000000));
        System.out.println("100,000.00元:" + Util.convertAmountToCn(10000000));
        System.out.println("100,100.00元:" + Util.convertAmountToCn(10010000));
        System.out.println("100,000.10元:" + Util.convertAmountToCn(10000010));
        System.out.println("1,000,000.00元:" + Util.convertAmountToCn(100000000));
        System.out.println("10,000,000.00元:" + Util.convertAmountToCn(1000000000));
        System.out.println("100,000,000.00元:" + Util.convertAmountToCn(Long.valueOf("10000000000")));
        System.out.println("1,000,000,000.00元:" + Util.convertAmountToCn(Long.valueOf("100000000000")));
        System.out.println("10,000,000,000.00元:" + Util.convertAmountToCn(Long.valueOf("1000000000000")));
        System.out.println("100,000,000,000.00元:" + Util.convertAmountToCn(BigDecimal.valueOf(Long.valueOf("100000000000"))));
        System.out.println("1,000,000,000,000.00元:" + Util.convertAmountToCn(BigDecimal.valueOf(Long.valueOf("1000000000000"))));
    }
}
