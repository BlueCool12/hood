package com.pyomin.hood.guestbook.exception;

import com.pyomin.hood.common.exception.ErrorCode;

public class GuestbookNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public GuestbookNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
