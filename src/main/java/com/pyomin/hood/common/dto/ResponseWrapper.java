package com.pyomin.hood.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper<T> {
    private final boolean success;
    private final String message;
    private final T data;    
}
