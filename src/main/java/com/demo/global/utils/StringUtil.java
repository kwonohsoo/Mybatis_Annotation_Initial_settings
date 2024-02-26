package com.demo.global.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StringUtil {

    /**
     * 핸드폰 번호 형식으로 변경(01012341234 -> 010-1234-1234)
     */
    public static String convertPhoneFormat(String number) {

        if (number == null) {
            return "";
        }

        String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
        return number.replaceAll(regEx, "$1-$2-$3");
    }

    /**
     * 문자를 숫자(double)로 변경
     */
    public static double stringToDouble(String string) {
        if (string == null) {
            return 0;
//            throw new NumberFormatException();
        }
        return Double.parseDouble(string);
    }

    /**
     * 스트링 리스트 항목 첫번째에 특정 문구 삽입
     */
    public static void addFirstIndexString(List<String> list, String name) {
        if (list == null || name == null)
            throw new IllegalArgumentException();
        list.add(0, name);
    }

    public static String convertIsNullHyphen(String string) {
        if (string == null) {
            return "-";
        }
        return string;
    }

    /**
     * 날짜를 타임 포멧으로 변경할 수 있다.
     * null 인 경우 "-" 반환하며 매개변수로 타임포멧을 지정하면 값을 얻을 수 있다.
     * yyyy-MM-dd HH:mm:ss, HH:mm:ss ...
     *
     * @param localDateTime 날짜
     * @param pattern       패턴
     */
    public static String convertTimeFormat(LocalDateTime localDateTime, String pattern) {
        if (localDateTime == null) {
            return "-";
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * @param coordinate 좌표
     * @return 12.1234
     */
    public static String convertCoordinate(String coordinate) {
        return String.format("%.4f", Double.valueOf(coordinate));
    }

    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

}
