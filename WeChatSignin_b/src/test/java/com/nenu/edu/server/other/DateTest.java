package com.nenu.edu.server.other;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Liang Jiayue
 * @Description:
 * @Date: 18:28 2020/7/4
 */
public class DateTest {

    @Test
    public void testDate() {
        String s1 = "20180425181212";
        String s2 = "20180425184232";

        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Date d1 = format.parse(s1);
            Date d2 = format.parse(s2);

            Long minute = (d1.getTime() - d2.getTime()) / 1000 / 60;
            System.out.println("minite: " + minute);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
