package com.green.attaparunever2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.user.*;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StompSessionManager implements ChannelInterceptor {
    private final ConcurrentHashMap<String, String> sessionUserMap = new ConcurrentHashMap<>();

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        // 연결 시, 세션 정보 등록
        if (accessor.getCommand() == StompCommand.CONNECT) {
            String sessionId = accessor.getSessionId();  // 세션 ID
            String userId = accessor.getFirstNativeHeader("userId");  // 헤더에서 userId 가져오기
            if(userId != null) {
                sessionUserMap.put(userId, sessionId);  // userId와 sessionId 매핑
            }
        } else if (accessor.getCommand() == StompCommand.DISCONNECT) {
            // 연결 종료 시, 세션 정보 제거
            String sessionId = accessor.getSessionId();
            sessionUserMap.values().remove(sessionId);
        }

        return message;
    }

    // 세션 ID 조회
    public String getSessionId(String userId) {
        return sessionUserMap.get(userId);
    }

    // 세션 제거
    public void removeSession(String userId) {
        sessionUserMap.remove(userId);
    }

    public void disconnectSession(String userId) {
        String sessionId = getSessionId(userId);  // 사용자 ID로 세션 ID 조회
        if (sessionId != null) {
            // 웹소켓 세션 종료
            StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.DISCONNECT);
            accessor.setSessionId(sessionId);
            // 세션 종료 이벤트를 트리거
            removeSession(userId);
        }
    }

    public void removeSubscription(String userId, String destination) {
        SimpUser simpUser = simpUserRegistry.getUser(userId);

        if (simpUser != null) {
            // 사용자의 세션들 순회
            for (SimpSession session : simpUser.getSessions()) {
                // 세션의 구독들을 순회
                session.getSubscriptions().removeIf(subscription -> subscription.getDestination().equals(destination));
            }
        }
    }
}
