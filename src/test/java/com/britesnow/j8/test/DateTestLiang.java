package com.britesnow.j8.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import org.junit.Test;

public class DateTestLiang {
    @Test
    public void LocalDateTest(){
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);
        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        ZonedDateTime  zonedDateTime = today.atStartOfDay(ZoneId.of("Asia/Shanghai"));
        
        LocalDateTime atStartOfDay = today.atStartOfDay();
        LocalDateTime localDateTime = today.atTime(12,23,39,2833829);
        LocalDateTime localDateTime2 = today.atTime(LocalTime.now());
        
        System.out.println("today: "+today);
        System.out.println("tomorrow: "+tomorrow);
        System.out.println("yesterday: "+yesterday);
        System.out.println("independenceDay: "+independenceDay);
        
        System.out.println("dayOfWeek: "+dayOfWeek);
        System.out.println("zonedDateTime: "+zonedDateTime);
        
        System.out.println("atStartOfDay: "+atStartOfDay);
        System.out.println("localDateTime: "+localDateTime);
        System.out.println("localDateTime2:"+localDateTime2);
        
        DateTimeFormatter germanFomatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
        LocalDate xmas = LocalDate.parse("04.08.2014",germanFomatter);
        System.out.println(xmas);

        //compareTo
        System.out.println("compareTo "+today.compareTo(tomorrow));
        System.out.println("compareTo "+tomorrow.compareTo(today));
        System.out.println("compareTo "+yesterday.compareTo(tomorrow));
        System.out.println("compareTo "+tomorrow.compareTo(yesterday));
        //equals
        System.out.println("equals "+today.equals(yesterday));
        
    }
    
    @Test
    public void LocalTimeDate(){
        LocalTime timeNow = LocalTime.now();
        System.out.println(timeNow);
        
        LocalTime now1 = LocalTime.now();
        LocalTime now2 = LocalTime.now();
        System.out.println(now1.isBefore(now2));
        System.out.println(now1.compareTo(now2));
        
    }
    
}
