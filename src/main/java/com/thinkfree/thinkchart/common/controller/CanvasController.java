package com.thinkfree.thinkchart.common.controller;

import com.thinkfree.thinkchart.common.dto.UserResponse;
import com.thinkfree.thinkchart.common.event.StompBroadcastEventListener;
import com.thinkfree.thinkchart.common.event.WsAction;
import com.thinkfree.thinkchart.common.event.WsMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import java.util.concurrent.ThreadLocalRandom;

@Controller
@RequiredArgsConstructor
public class CanvasController {

    private final StompBroadcastEventListener eventListener;

    @MessageMapping("/canvas/join")
    @SendToUser(value = "/queue/me", broadcast = false)
    public WsMessage<UserResponse> join(SimpMessageHeaderAccessor headerAccessor) {

        int colorNumber = (eventListener.getConnectionCount() - 1)  % 3 + 1;

        UserResponse response = new UserResponse(
                headerAccessor.getSessionId(),
                String.valueOf(colorNumber)
        );

        return new WsMessage<>(WsAction.USER_ASSIGNED, response);
    }
}