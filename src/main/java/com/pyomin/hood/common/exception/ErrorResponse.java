package com.pyomin.hood.common.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {    
    private String message;
    private String errorCode;
    private String details;
    private LocalDateTime timestamp;    
    
}
