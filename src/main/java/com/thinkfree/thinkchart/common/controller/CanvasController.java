package com.thinkfree.thinkchart.common.controller;

import com.thinkfree.thinkchart.common.event.WsAction;
import com.thinkfree.thinkchart.common.event.WsMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CanvasController {

    @MessageMapping("/canvas/join")
    @SendToUser(value = "/queue/me", broadcast = false)
    public WsMessage<Map<String, String>> join(SimpMessageHeaderAccessor headerAccessor) {
        String sessionId = headerAccessor.getSessionId();
        Map<String, String> response = new HashMap<>();
        response.put("userId", sessionId);

        return new WsMessage<>(WsAction.USER_ASSIGNED, response);
    }
}