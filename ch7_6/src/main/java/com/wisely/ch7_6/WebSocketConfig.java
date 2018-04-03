package com.wisely.ch7_6;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 配置 WebSocket 广播式：
 * 1 通过 @EnableWebSocketMessageBroker 注解开启使用 STOMP 协议来传输基于代理（message broker）的消息，这时控制器支持使用@messageMapping，就像使用@RequestMapping一样。
 * 2 注册 STOMP 协议的节点（endpoint），并映射的指定的URL。
 * 3 注册一个 STOMP 的 endpoint，并指定使用 SockJS 协议。
 * 4 配置消息代理（message Broker）。
 * 5 广播式应配置一个 /topic 消息代理。
 * 
 */
@Configuration
@EnableWebSocketMessageBroker//1
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {//2
        registry.addEndpoint("/endpointWisely").withSockJS(); //3
        registry.addEndpoint("/endpointChat").withSockJS(); 
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {//4
        registry.enableSimpleBroker("/queue","/topic"); //5
    }

}
