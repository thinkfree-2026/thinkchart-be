package com.thinkfree.thinkchart.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    CIRCLE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 원을 찾을 수 없습니다."),
    CHART_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 차트를 찾을 수 없습니다."),
    ALREADY_USED_CIRCLE(HttpStatus.CONFLICT, "이미 사용된 원입니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "요청 값이 올바르지 않습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
