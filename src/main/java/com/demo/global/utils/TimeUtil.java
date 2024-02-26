package com.demo.global.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

public class TimeUtil {

    /**
     * 현재 날짜 시간과 파라미터 날짜 시간의 차이(초)
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static long getDateNowDiffSecond(String date) throws ParseException {

        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Date failedTime = fm.parse(date);
        long diff = (now.getTime() - failedTime.getTime()) / (60 * 1000);

        return diff;
    }

    /**
     * 현재 날짜 시간과 파라미터 날짜 시간의 차이(일)
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static long getDateNowDiffDay(String date) throws ParseException {

        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Date failedTime = fm.parse(date);
        long diff = (now.getTime() - failedTime.getTime()) / (24 * 60 * 60 * 1000);

        return diff;
    }

    /**
     * 해당 날짜에서 2년전 다음 분기의 첫째날
     *
     * @param date
     * @return
     */
    public static String firstDayFromTwoYearsBeforeNextDateQuarter(String date) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = LocalDate.parse(date, formatter);
        int quarter = ld.get(IsoFields.QUARTER_OF_YEAR); // Get the Quarter, 1, 2, 3, 4
        // Then create a new date with new quarter * 3 and last day of month
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        return ld.withMonth(quarter * 3).minusYears(2).plusMonths(1).with(TemporalAdjusters.firstDayOfMonth()).format(formatter);
    }

    /**
     * 해당 분기의 마지막 일
     *
     * @param date
     * @return
     */
    public static String lastDayFromDateQuarter(String date) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = LocalDate.parse(date, formatter);
        int quarter = ld.get(IsoFields.QUARTER_OF_YEAR); // Get the Quarter, 1, 2, 3, 4
        // Then create a new date with new quarter * 3 and last day of month
        return ld.withMonth(quarter * 3).with(TemporalAdjusters.lastDayOfMonth()).format(formatter);
    }

    /**
     * 다음 분기의 첫째 일
     *
     * @param date
     * @return
     */
    public static String firstDayFromDateNextQuarter(String date) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = LocalDate.parse(date, formatter);
        int quarter = ld.get(IsoFields.QUARTER_OF_YEAR); // Get the Quarter, 1, 2, 3, 4
        // Then create a new date with new quarter * 3 and last day of month
        return ld.withMonth(quarter * 3).plusMonths(1).with(TemporalAdjusters.firstDayOfMonth()).format(formatter);
    }

    /**
     * 해당 날짜의 값을 회사의 타임존 시간만큼 차감
     *
     * @param searchTime
     * @param companyTimezoneSec
     * @return
     * @throws ParseException
     */
    public static String convertCompanyTimezoneToUTC(String searchTime, int companyTimezoneSec) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = df.parse(searchTime);
        Long timeUnix = date.getTime();
        timeUnix -= (companyTimezoneSec * 1000);
        Date newDate = new Date(timeUnix);

        return df.format(newDate).toString();
    }

    /**
     * 현재 날짜 시간과 db 시간차이
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getConvertDataBaseDiffDay(String date, SimpleDateFormat transFormat) throws ParseException {

        Calendar cal = Calendar.getInstance();
        try {
            Date datws = transFormat.parse(date);
            cal.setTime(datws);
            cal.add(Calendar.HOUR, -9);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return transFormat.format(cal.getTime());
    }


    /**
     * 현재 날짜 시간과 파라미터 날짜 시간의 차이(초)
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getDateNowDiff(String date) throws ParseException {

        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fm.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        Date now = new Date();
        Date failedTime = fm.parse(date);
//    long diff = (now.getTime() - failedTime.getTime()) / (60 * 1000);
        long diff = (now.getTime() - failedTime.getTime()) / 1000;

        return String.format("%02d", diff / 3600) + ":" + String.format("%02d", diff % 3600 / 60) + ":" + String.format("%02d", diff % 60 % 60);
    }

    /**
     * 파라미터 날짜 시간과 파라미터 날짜 시간의 차이(초)
     *
     * @param startdate, enddate
     * @return
     * @throws ParseException
     */
    public static String getParamDateDiff(String startdate, String enddate) throws ParseException {

        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startfailedTime = fm.parse(startdate);
        Date endfailedTime = fm.parse(enddate);
//    long diff = (now.getTime() - failedTime.getTime()) / (60 * 1000);
        long diff = (endfailedTime.getTime() - startfailedTime.getTime()) / 1000;

        return String.format("%02d", (diff / 3600 > 0) ? diff / 3600 : diff / 3600 * -1) + ":"
                + String.format("%02d", (diff % 3600 / 60 > 0) ? diff % 3600 / 60 : diff % 3600 / 60 * -1) + ":"
                + String.format("%02d", (diff % 60 % 60 > 0) ? diff % 60 % 60 : diff % 60 % 60 * -1);
    }

    /**
     * 두 날짜 사이의 시간 차이
     * @param startTime 시작 시간
     * @param endTime 종료 시간 (null 인 경우 현재 시간으로 계산)
     * @return 시간 차이
     */
    public static LocalDateTime getDiffTime(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null && endTime == null) {
            return null;
        }
        // 종료 시간이 null 인 경우 현재 시간으로 계산
        long seconds = ChronoUnit.SECONDS.between(startTime, Objects.requireNonNullElseGet(endTime, LocalDateTime::now));
        if (seconds < 0) {
            return null;
        }
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(seconds), ZoneId.of("UTC"));
    }
}
