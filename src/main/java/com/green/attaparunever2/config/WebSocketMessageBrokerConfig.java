package com.green.attaparunever2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // STOMP를 사용한 메시징 처리 활성화
@RequiredArgsConstructor
public class WebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {
    private final WebSocketHandshakeInterceptor handshakeInterceptor;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 클라이언트가 메시지를 보내는 목적지 "/app"으로 설정
        config.setApplicationDestinationPrefixes("/app");

        // 서버가 클라이언트로 메시지를 브로드캐스트하는 목적지 "/topic"과 "/queue"로 설정
        config.enableSimpleBroker("/topic", "/queue");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // "/ws-stomp" 엔드포인트에 WebSocket 연결을 처리하도록 설정
        registry.addEndpoint("/ws-stomp")
                .setAllowedOrigins("http://127.0.0.1:5500") // 허용할 오리진 추가
                .addInterceptors(handshakeInterceptor) // 인터셉터 등록
                .withSockJS(); // SockJS를 사용할 경우
    }
}

