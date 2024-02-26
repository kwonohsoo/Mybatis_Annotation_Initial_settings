package com.demo.global.error.exception;

import com.demo.global.error.code.CommonErrorCode;
import com.demo.global.error.code.ErrorCode;

public class EnumMappingException extends BusinessException {

    public EnumMappingException() {
        super(CommonErrorCode.ENUM_MAPPING_NOT_FOUND);
    }

    public EnumMappingException(ErrorCode errorCode) {
        super(errorCode);
    }

}
