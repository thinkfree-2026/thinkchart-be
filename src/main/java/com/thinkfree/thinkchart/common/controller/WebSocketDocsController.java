package com.thinkfree.thinkchart.common.controller;

import com.thinkfree.thinkchart.circle.dto.UpdateCircleRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


@Tag(name = "WebSocket API (문서용)", description = "실제로는 WebSocket(STOMP)으로 동작함. <br> 연결: ws://localhost:8080/ws-stomp")
@RestController
public class WebSocketDocsController {


    @Operation(summary = "웹소켓 연결", description = "Destination: ws://localhost:8080/ws-stomp")
    @RequestMapping(path = "/docs/websocket/connect", method = RequestMethod.OPTIONS)
    public void webSocketConnectDocs() {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }

    @Operation(summary = "실시간 커서 공유 (WebSocket)", description = "Destination: /app/canvas/cursor <br> Subscribe: /topic/canvas")
    @RequestMapping(path = "/docs/app/canvas/cursor", method = RequestMethod.OPTIONS)
    public void cursorMoveDocs() {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }

    @Operation(summary = "원 그리기 (WebSocket)", description = "Destination: /app/canvas/draw <br> Subscribe: /topic/canvas")
    @RequestMapping(path = "/docs/app/canvas/draw", method = RequestMethod.OPTIONS)
    public void circleDrawDocs(@RequestBody UpdateCircleRequest request) {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }

    @Operation(summary = "원 크기 조정 (WebSocket)", description = "Destination: /app/canvas/resize <br> Subscribe: /topic/canvas")
    @RequestMapping(path = "/docs/app/canvas/resize", method = RequestMethod.OPTIONS)
    public void circleResizeDocs(@RequestBody UpdateCircleRequest request) {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }

    @Operation(summary = "원 포커스 (WebSocket)", description = "Destination: /app/canvas/focus <br> Subscribe: /topic/canvas")
    @RequestMapping(path = "/docs/app/canvas/focus", method = RequestMethod.OPTIONS)
    public void circleFocusDocs(@RequestBody UpdateCircleRequest request) {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }


}