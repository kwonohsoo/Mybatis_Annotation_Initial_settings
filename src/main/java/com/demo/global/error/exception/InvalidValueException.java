package com.demo.global.error.exception;

import com.demo.global.error.code.CommonErrorCode;
import com.demo.global.error.code.ErrorCode;

public class InvalidValueException extends BusinessException {

    public InvalidValueException() {
        super(CommonErrorCode.INVALID_TYPE_VALUE);
    }

    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }

}
