package com.demo.global.enums;


import com.demo.global.error.exception.EnumMappingException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.EnumSet;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumValueConverterUtils {

    public static <T extends Enum<T> & EnumType> T findByCode(Class<T> enumClass, int code) {
        return EnumSet.allOf(enumClass).stream()
                .filter(item -> item.getCode() == code)
                .findAny()
                .orElseThrow(EnumMappingException::new);
    }

    public static <T extends Enum<T> & EnumType> Integer toCode(T enumValue) {
        return enumValue.getCode();
    }
}
