package com.thinkfree.thinkchart.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 클라이언트가 WebSocket 서버에 연결하는 데 사용할 수 있는 STOMP 프로토콜 엔드포인트를 등록.
     *
     * @param registry STOMP 엔드포인트를 구성하는 데 사용되는 {@link StompEndpointRegistry}
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*");
    }

    /**
     * 서버와 클라이언트 간의 메시징을 처리하기 위해 메시지 브로커를 구성.
     * 애플리케이션 목적지 Prefix를 설정하고, 메시지 브로드캐스팅을 위한
     * 지정된 목적지를 지원하는 단순 인메모리 브로커를 활성화
     *
     * @param registry 메시지 브로커 옵션 구성을 허용하는 {@link MessageBrokerRegistry}
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app")
                .enableSimpleBroker("/topic", "/queue");
    }
}