package com.britesnow.j8.test;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalQueries;
import java.util.Date;
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
        String formatDate = today.format(germanFomatter);
        System.out.println("FormatDate: "+formatDate);
        
        //parse
        System.out.println("");
        System.out.println("parse: ");
        LocalDate parse1 = LocalDate.parse("04.08.2014",germanFomatter);
        System.out.println("parse1: "+parse1);
        LocalDate parse2 = LocalDate.parse("2014-08-04");
        System.out.println("parse2: "+parse2);
        
        //compareTo
        System.out.println("");
        System.out.println("compareTo: ");
        System.out.println("compareTo "+today.compareTo(tomorrow));
        System.out.println("compareTo "+tomorrow.compareTo(today));
        System.out.println("compareTo "+yesterday.compareTo(tomorrow));
        System.out.println("compareTo "+tomorrow.compareTo(yesterday));
        
        //equals
        System.out.println("");
        System.out.println("equals/isAfter/isBefore: ");
        System.out.println("equals "+today.equals(yesterday));
        System.out.println("isAfter "+today.isAfter(yesterday));
        System.out.println("isBefore "+today.isBefore(yesterday));
        
        //get
        System.out.println("");
        System.out.println("get: ");
        System.out.println("getChronology: "+today.getChronology());
        System.out.println("getDayOfYear: "+today.getDayOfYear());
        System.out.println("getDayOfMonth: "+today.getDayOfMonth());
        System.out.println("getMonthValue: "+today.getMonthValue());
        System.out.println("getYear: "+today.getYear());
        System.out.println("getMonth: "+today.getMonth());
        System.out.println("getDayOfWeek: "+today.getDayOfWeek());
        System.out.println("getEra: "+today.getEra());
        System.out.println("getLong: "+today.getLong(ChronoField.DAY_OF_MONTH));
        
        
        System.out.println("hashCode: "+today.hashCode());
        
        
        //minus
        
        System.out.println("");
        System.out.println("minus:");
        System.out.println("minus: "+today.minus(2,ChronoUnit.YEARS));
        System.out.println("minus2: "+today.minus(Period.ofMonths(5)));
        System.out.println("minusDays: "+today.minusDays(2));
        System.out.println("minusMonths"+today.minusMonths(2));
        System.out.println("minusWeeks: "+today.minusWeeks(2));
        System.out.println("minusYears: "+today.minusYears(2));
        
        //plus
        System.out.println("");
        System.out.println("plus:");
        System.out.println("plus: "+today.plus(2, ChronoUnit.YEARS));
        System.out.println("plus2: "+today.plus(Period.ofMonths(5)));
        System.out.println("plusDays: "+today.plusDays(2));
        System.out.println("plusMonths: "+today.plusMonths(2));
        System.out.println("plusWeeks: "+today.plusWeeks(2));
        System.out.println("plusYears: "+today.plusYears(2));
        
        //now
        System.out.println("");
        System.out.println("now:");
        System.out.println("now: "+ LocalDate.now());
        System.out.println("now2: "+ LocalDate.now(ZoneId.of("Europe/Paris")));
        System.out.println("now3: "+ LocalDate.now(Clock.systemUTC()));
        
        //of
        System.out.println("");
        System.out.println("of:");
        System.out.println("of1: "+ LocalDate.of(2014, 04, 14));
        System.out.println("of2: "+ LocalDate.of(2014, Month.APRIL,14));
        System.out.println("of3: "+ LocalDate.ofEpochDay(33));
        System.out.println("of4: "+ LocalDate.ofYearDay(2014, 89));
        
        //with
        System.out.println("");
        System.out.println("with:");
        System.out.println("with1: "+ today.with(yesterday));
        System.out.println("with2: "+ today.with(ChronoField.DAY_OF_MONTH, 3));
        System.out.println("withDayOfMonth: "+ today.withDayOfMonth(3));
        System.out.println("withDayOfYear: "+ today.withDayOfYear(88));
        System.out.println("withMonth: "+ today.withMonth(6));
        System.out.println("withYear: "+ today.withYear(2013));
        System.out.println("with: "+ today.withYear(2013).withMonth(6).withDayOfMonth(3));
        
        
        System.out.println("");
        System.out.println("query: "+localDateTime.query(TemporalQueries.localDate()));
        System.out.println("range: "+localDateTime.range(ChronoField.DAY_OF_MONTH));
        System.out.println("range2: "+localDateTime.range(ChronoField.MONTH_OF_YEAR));
        System.out.println("range3: "+localDateTime.range(ChronoField.HOUR_OF_DAY));
        
        System.out.println("toEpochDay: "+today.toEpochDay());
        System.out.println("toString: "+localDateTime.toString());
        
        System.out.println("until: "+today.until(yesterday, ChronoUnit.DAYS));
        System.out.println("until: "+today.until(yesterday));
        
        
    }
    
    @Test
    public void LocalTimeDate(){
        LocalTime timeNow = LocalTime.now();
        LocalTime timeNow2 = LocalTime.now(Clock.systemUTC());
        LocalTime timeNow3 = LocalTime.now(ZoneId.of("Europe/Paris"));
        
        //now
        System.out.println("");
        System.out.println("LocalTime:");
        System.out.println("time now: "+timeNow);
        System.out.println("time now2: "+timeNow2);
        System.out.println("time now3: "+timeNow3);
        
        //plus
        LocalTime plus = timeNow.plus(2,ChronoUnit.HOURS);
        LocalTime plus2 = timeNow.plus(Duration.ofHours(5));
        LocalTime plusHours = timeNow.plusHours(2);
        LocalTime plusMinutes = timeNow.plusMinutes(2);
        LocalTime plusSeconds = timeNow.plusSeconds(20);
        LocalTime plusNanos = timeNow.plusNanos(33);
        
        System.out.println("");
        System.out.println("plus:");
        System.out.println("plus: "+plus);
        System.out.println("plus2: "+plus2);
        System.out.println("plusHours: "+plusHours);
        System.out.println("plusMinutes: "+plusMinutes);
        System.out.println("plusSeconds: "+plusSeconds);
        System.out.println("plusNanos: "+plusNanos);
        System.out.println("");
        
        System.out.println("adjustInto: "+plusHours.adjustInto(timeNow));
        System.out.println("atDate: "+timeNow.atDate(LocalDate.now()));
        System.out.println("atOffset: "+timeNow.atOffset(ZoneOffset.UTC));
        
        System.out.println("");
        System.out.println("equals: "+timeNow.equals(plus));
        System.out.println("compareTo: "+timeNow.compareTo(plus));
        System.out.println("isBefore: "+timeNow.isBefore(plus));
        System.out.println("isAfter: "+timeNow.isAfter(plus));
        System.out.println("isSupported: "+timeNow.isSupported(ChronoField.MINUTE_OF_HOUR));
        System.out.println("isSupportedUnit: "+timeNow.isSupported(ChronoUnit.HOURS));
        
        
        
        
        DateTimeFormatter germanFomatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
        String formatDate = timeNow.format(germanFomatter);
        System.out.println("FormatDate: "+formatDate);
        
        //get
        System.out.println("");
        System.out.println("get: ");
        System.out.println("getMin: "+timeNow.get(ChronoField.MINUTE_OF_HOUR));
        System.out.println("getHour: "+timeNow.getHour());
        System.out.println("getMinute: "+timeNow.getMinute());
        System.out.println("getLong: "+timeNow.getLong(ChronoField.HOUR_OF_AMPM));
        System.out.println("getNano: "+timeNow.getNano());
        System.out.println("getSecond: "+timeNow.getSecond());
        
    }
    @Test
    public void colckTest(){
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);   // legacy java.util.Date
        
        System.out.println("millis: "+millis);
        System.out.println("instant: "+instant);
        System.out.println("legacyDate: "+legacyDate);
    }
    @Test
    public void timeZoneTest(){
        System.out.println(ZoneId.getAvailableZoneIds());
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());
    }
    
}
