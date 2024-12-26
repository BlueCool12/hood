package com.pyomin.hood.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    GUESTBOOK_NOT_FOUND("GUESTBOOK_NOT_FOUND", "방명록을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    GUESTBOOK_PASSWORD_MISMATCH("GUESTBOOK_PASSWORD_MISMATCH", "방명록 비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

}