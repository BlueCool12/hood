package com.pyomin.hood.common.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {    
    private String message;
    private String errorCode;
    private String details;
    private LocalDateTime timestamp;
}
