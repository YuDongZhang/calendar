package com.example.customcalendarview;



import org.joda.time.Days;
import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test(){
        // 当前日期
        LocalDate today = LocalDate.now();

        // 构建一个过去的日期
        LocalDate oldDate = LocalDate.parse("2023-09-01");

        // 使用Days类计算天数差
//        long daysBetween = ChronoUnit.DAYS.between(oldDate, today);

//        System.out.println("xxxx"+daysBetween);
        // 打印结果例如: 156

        // 也可以用Days的daysBetween方法
//        long daysDiff = java.time.Days.daysBetween(oldDate, today).getDays();
//        System.out.println(daysDiff);
    }
}