package com.thinkfree.thinkchart.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiResponseCode {

    // 시스템/연결 관련
    SOCKET_CONNECTED("SOCKET_CONNECTED", "소켓이 연결되었습니다."),

    // 캔버스 제어 관련
    CANVAS_LOADED("CANVAS_LOADED", "캔버스 데이터를 불러왔습니다."),
    CANVAS_RESET("CANVAS_RESET", "캔버스가 초기화되었습니다."),
    CANVAS_SAVED("CANVAS_SAVED", "캔버스 데이터가 저장되었습니다."),

    // 원 제어 관련
    CIRCLE_CREATED("CIRCLE_CREATED", "원이 생성되었습니다."),
    CIRCLE_UPDATED("CIRCLE_UPDATED", "원이 수정되었습니다."),
    CIRCLE_DELETED("CIRCLE_DELETED", "원이 삭제되었습니다."),

    // 차트 제어 관련
    CHART_CREATED("CHART_CREATED", "차트가 생성되었습니다."),
    CHART_SUCCESS("CHART_SUCCESS", "차트가 조회되었습니다."),
    CHART_RESIZED("CHART_RESIZED", "차트 데이터 값이 변경되었습니다."),
    CHART_COLOR_UPDATED("CHART_COLOR_UPDATED", "차트 색상이 변경되었습니다."),
    CHART_X_NAME_UPDATED("CHART_X_NAME_UPDATED", "차트 X축 이름이 변경되었습니다."),
    CHART_Y_NAME_UPDATED("CHART_Y_NAME_UPDATED", "차트 Y축 이름이 변경되었습니다."),
    CHART_NAME_UPDATED("CHART_NAME_UPDATED", "차트 이름이 변경되었습니다."),
    CHART_DELETED("CHART_DELETED", "차트가 삭제되었습니다.");

    private final String code;
    private final String message;
}