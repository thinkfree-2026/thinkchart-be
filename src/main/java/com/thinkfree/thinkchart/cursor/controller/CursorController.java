package com.thinkfree.thinkchart.cursor.controller;

import com.thinkfree.thinkchart.common.dto.WsAction;
import com.thinkfree.thinkchart.common.dto.WsMessage;
import com.thinkfree.thinkchart.cursor.dto.CursorMovePayload;
import jakarta.validation.Valid;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CursorController {

    @MessageMapping("/canvas/cursor")
    @SendTo("/topic/canvas")
    public WsMessage<CursorMovePayload> shareCursor(@Valid @Payload CursorMovePayload payload) {
        return new WsMessage<>(WsAction.CURSOR_MOVE, payload);
    }
}
