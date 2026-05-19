package com.thinkfree.thinkchart.common.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class StompBroadcastEventListener {

    private final SimpMessagingTemplate messagingTemplate;
    private final Set<String> sessionIds = ConcurrentHashMap.newKeySet();

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(StompBroadcastEvent event) {
        messagingTemplate.convertAndSend(
                event.getDestination(),
                event.getMessage()
        );
    }

    @EventListener
    public void handleConnect(SessionConnectedEvent event) {
        String sessionId = SimpMessageHeaderAccessor.getSessionId(
                event.getMessage().getHeaders()
        );

        if (sessionId != null) {
            sessionIds.add(sessionId);
        }
    }

    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        sessionIds.remove(sessionId);

        messagingTemplate.convertAndSend(
                "/topic/canvas",
                new WsMessage<>(WsAction.CURSOR_LEAVE, Map.of("id", sessionId))
        );
    }

    public int getConnectionCount() {
        return sessionIds.size();
    }
}