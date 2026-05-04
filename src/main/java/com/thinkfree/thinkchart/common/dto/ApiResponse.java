package com.thinkfree.thinkchart.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    private final String message;
    private final T data;
    private final LocalDateTime timestamp;

    public static <T> ApiResponse<T> of(ApiResponseCode apiResponseCode, T data) {
        return new ApiResponse<>(true, apiResponseCode.getMessage(), data, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> of(ApiResponseCode apiResponseCode) {
        return new ApiResponse<>(true, apiResponseCode.getMessage(), null, LocalDateTime.now());
    }

}
