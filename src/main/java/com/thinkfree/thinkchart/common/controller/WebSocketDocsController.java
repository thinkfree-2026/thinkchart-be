package com.thinkfree.thinkchart.common.controller;

import com.thinkfree.thinkchart.chart.dto.UpdateBarRequest;
import com.thinkfree.thinkchart.circle.dto.CreateCircleRequest;
import com.thinkfree.thinkchart.circle.dto.UpdateCircleRequest;
import com.thinkfree.thinkchart.cursor.dto.CursorMovePayload;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


@Tag(name = "1. WebSocket API(문서용)", description = "실제로는 WebSocket(STOMP)으로 동작함. <br> 연결: ws://thinkchart.kro.kr:8080/ws")
@RestController
public class WebSocketDocsController {


    @Operation(summary = "웹소켓 연결", description = "Destination: ws://thinkchart.kro.kr:8080/ws")
    @RequestMapping(path = "ws://thinkchart.kro.kr:8080/ws", method = RequestMethod.OPTIONS)
    public void webSocketConnectDocs() {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }

    @Operation(
            summary = "웹소켓 액션 목록",
            description =
                    "    CURSOR_ENTER,\n" +
                    "    CURSOR_MOVE,\n" +
                    "    CURSOR_LEAVE,\n" +
                    "\n" +
                    "    CIRCLE_DRAW_START,\n" +
                    "    CIRCLE_DRAW_UPDATE,\n" +
                    "\n" +
                    "    CIRCLE_CREATED,\n" +
                    "    CIRCLE_UPDATED,\n" +
                    "    CIRCLE_DELETED,\n" +
                    "    CIRCLE_FOCUSED,\n" +
                    "\n" +
                    "    CHART_CREATED,\n" +
                    "    CHART_UPDATED,\n" +
                    "    CHART_DELETED,\n" +
                    "\n" +
                    "    CHART_BAR_UPDATED,\n" +
                    "    CHART_BAR_DELETED"
    )
    @RequestMapping(path = "/docs/app/canvas/actions", method = RequestMethod.OPTIONS)
    public void webSocketActionsDocs() {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }

    @Operation(summary = "실시간 커서 공유", description = "Destination: /app/canvas/cursor <br> Subscribe: /topic/canvas")
    @RequestMapping(path = "/docs/app/canvas/cursor", method = RequestMethod.OPTIONS)
    public void cursorMoveDocs(CursorMovePayload payload) {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }

    @Operation(summary = "차트 모달 커서 공유", description = "Destination: /app/canvas/charts/{chartId}/cursor <br> Subscribe: /topic/canvas/charts/{chartId}")
    @RequestMapping(path = "/docs/app/canvas/charts/{chartId}/cursor", method = RequestMethod.OPTIONS)
    public void chartModalCursorMoveDocs(CursorMovePayload payload) {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }

    @Operation(summary = "차트 모달 바 수정", description = "Destination: /app/canvas/charts/{chartId}/{barId} <br> Subscribe: /topic/canvas/charts/{chartId}")
    @RequestMapping(path = "/docs/app/canvas/charts/{chartId}/{barId}", method = RequestMethod.OPTIONS)
    public void chartModalBarResizeDocs(UpdateBarRequest payload) {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }

    @Operation(summary = "원 그리기", description = "Destination: /app/canvas/draw (미사용)<br> Subscribe: /topic/canvas")
    @RequestMapping(path = "/docs/app/canvas/draw", method = RequestMethod.OPTIONS)
    public void circleDrawDocs(@RequestBody CreateCircleRequest request) {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }

    @Operation(summary = "원 크기 조정", description = "Destination: /app/canvas/resize (미사용) <br> Subscribe: /topic/canvas")
    @RequestMapping(path = "/docs/app/canvas/resize", method = RequestMethod.OPTIONS)
    public void circleResizeDocs(@RequestBody UpdateCircleRequest request) {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }

    @Operation(summary = "원 포커스", description = "Destination: /app/canvas/focus (미사용) <br> Subscribe: /topic/canvas")
    @RequestMapping(path = "/docs/app/canvas/focus", method = RequestMethod.OPTIONS)
    public void circleFocusDocs(@RequestBody UpdateCircleRequest request) {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }


}