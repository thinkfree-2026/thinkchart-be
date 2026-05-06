package com.thinkfree.thinkchart.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thinkfree.thinkchart.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ApiResponse<T> {

    private final boolean success;
    private final String code;
    private final String message;
    private final T data;
    private final LocalDateTime timestamp;

    public static <T> ApiResponse<T> success(ApiResponseCode apiResponseCode, T data) {
        return new ApiResponse<>(true, apiResponseCode.getCode(), apiResponseCode.getMessage(), data, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> success(ApiResponseCode apiResponseCode) {
        return new ApiResponse<>(true, apiResponseCode.getCode(), apiResponseCode.getMessage(), null, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode, T data) {
        return new ApiResponse<>(false, errorCode.getCode(), errorCode.getMessage(), data, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        return new ApiResponse<>(false, errorCode.getCode(), errorCode.getMessage(), null, LocalDateTime.now());
    }
}
