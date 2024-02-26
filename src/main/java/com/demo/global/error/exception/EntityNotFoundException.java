package com.demo.global.error.exception;

import com.demo.global.error.code.CommonErrorCode;
import com.demo.global.error.code.ErrorCode;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException() {
        super(CommonErrorCode.ENTITY_NOT_FOUND);
    }

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

}
