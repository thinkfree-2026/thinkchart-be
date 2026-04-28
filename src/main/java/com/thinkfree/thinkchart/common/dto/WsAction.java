package com.thinkfree.thinkchart.common.dto;

public enum WsAction {
    CURSOR_ENTER,
    CURSOR_MOVE,
    CURSOR_LEAVE,

    CIRCLE_DRAW_START,
    CIRCLE_DRAW_UPDATE,

    CIRCLE_CREATED,
    CIRCLE_UPDATED,
    CIRCLE_DELETED,
    CIRCLE_FOCUSED,

    CHART_CREATED,
    CHART_UPDATED,
    CHART_DELETED
}
