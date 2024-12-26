package com.pyomin.hood.guestbook.exception;

import com.pyomin.hood.common.exception.ErrorCode;

public class GuestbookNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public GuestbookNotFoundException() {
        super(ErrorCode.GUESTBOOK_NOT_FOUND.getMessage());
        this.errorCode = ErrorCode.GUESTBOOK_NOT_FOUND;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
