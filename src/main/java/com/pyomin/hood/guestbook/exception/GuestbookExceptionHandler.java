package com.pyomin.hood.guestbook.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pyomin.hood.common.exception.ErrorResponse;

@RestControllerAdvice
public class GuestbookExceptionHandler {

    @ExceptionHandler(GuestbookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleGuestbookNotFound(GuestbookNotFoundException e) {
        return ResponseEntity.badRequest().body(ErrorResponse.from(e.getErrorCode()));
    }

    @ExceptionHandler(GuestbookPasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> handleGuestbookPasswordMismatch(GuestbookPasswordMismatchException e) {
        return ResponseEntity.badRequest().body(ErrorResponse.from(e.getErrorCode()));
    }

}
