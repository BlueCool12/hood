package com.pyomin.hood.guestbook.exception;

import com.pyomin.hood.common.exception.ErrorCode;

public class GuestbookPasswordMismatchException extends RuntimeException {

    private final ErrorCode errorCode;

    public GuestbookPasswordMismatchException() {
        super(ErrorCode.GUESTBOOK_PASSWORD_MISMATCH.getMessage());
        this.errorCode = ErrorCode.GUESTBOOK_PASSWORD_MISMATCH;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
