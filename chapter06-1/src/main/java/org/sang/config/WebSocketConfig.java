package org.sang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 自定义类 WebSocketConfig 继承自 WebSocketMessageBrokerConfigurer 进行 WebSocket 配置
 */
@Configuration
// @EnableWebSocketMessageBroker 注解开启 WebSocket 消息代理
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 设置消息代理前缀
        // 即如果消息前缀是 /topic 就会将消息转发给消息代理（broker），再由消息代理将消息广播给当前连接的客户端
        registry.enableSimpleBroker("/topic");
        // 配置一个或多个前缀，通过这些前缀过滤出需要被注解方法处理的消息
        // 例如，前缀 /app 的 destination 可以通过 @MessageMapping 注解的方法处理
        // 而其他 destination（/topic  /queue） 将被直接交给 broker 处理
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 定义一个前缀为 /chat 的 endPoint， 并开启 sockjs 支持，sockjs 可以解决浏览器对 WebSocket 的兼容性问题
        // 客户端将通过这里配置的 URL 来建立 WebSocket 连接
        registry.addEndpoint("/chat").withSockJS();
    }
}
