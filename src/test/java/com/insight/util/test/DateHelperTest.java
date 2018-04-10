package com.insight.util.test;

import com.insight.util.DateHelper;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Date;

public class DateHelperTest extends TestCase {


    @Test
    public void testDateHelper() {
        System.out.println(DateHelper.getDate());
        System.out.println(DateHelper.getDateTime());
        System.out.println(DateHelper.getDateTime(" \"yyyy-MM-dd HH:mm:ss\""));
        System.out.println(DateHelper.parseDate("20170904"));
        System.out.println(DateHelper.parseDateTime("20170904010101"));
        System.out.println(DateHelper.formatDate(new Date()));
        System.out.println(DateHelper.validateFormat("2017-09-05", "yyyyMMdd"));
        System.out.println(DateHelper.validateFormat("2017-09-05", "yyyy-MM-dd"));
        System.out.println(DateHelper.getWeek());
        System.out.println(DateHelper.getWeek("20170902"));



        String date = "1824/1/1";

        try {
            String res = DateHelper.dateFormat(date);

            int i = 0;
        }catch(Exception ex){

        }
    }

}
