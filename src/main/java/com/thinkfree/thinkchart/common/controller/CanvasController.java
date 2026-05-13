package com.thinkfree.thinkchart.common.controller;

import com.thinkfree.thinkchart.common.dto.UserResponse;
import com.thinkfree.thinkchart.common.event.WsAction;
import com.thinkfree.thinkchart.common.event.WsMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.concurrent.ThreadLocalRandom;

@Controller
@RequiredArgsConstructor
public class CanvasController {

    @MessageMapping("/canvas/join")
    @SendToUser(value = "/queue/me", broadcast = false)
    public WsMessage<UserResponse> join(SimpMessageHeaderAccessor headerAccessor) {

        UserResponse response = new UserResponse(
                headerAccessor.getSessionId(),
                randomVisibleHexColor()
        );

        return new WsMessage<>(WsAction.USER_ASSIGNED, response);
    }

    private String randomVisibleHexColor() {
        int r = ThreadLocalRandom.current().nextInt(50, 231);
        int g = ThreadLocalRandom.current().nextInt(50, 231);
        int b = ThreadLocalRandom.current().nextInt(50, 231);

        return String.format("#%02X%02X%02X", r, g, b);
    }
}