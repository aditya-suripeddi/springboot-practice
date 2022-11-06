package com.dailycodebuffer.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker // enable websocket message handling backed by a broker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // registers the /stomp-endpoint, enabling SockJS fallback options so that
        // alternate transports can be used if WebSocket is not available.
        //
        // The SockJS client will attempt to connect to /stomp-endpoint and use the
        // best available transport (websocket, xhr-streaming, xhr-polling, and so on).

        registry.addEndpoint("/stomp-endpoint")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        //  enable a simple in-memory based message broker to
        //  carry the greeting messages back to the client on
        //  destinations prefixed with /topic
        //
        // so if sockJsClient listens on /topic/greetings it will receive
        // messages sent by server as it has /topic prefix
        registry.enableSimpleBroker("/topic");


        // prefix "/app" for methods annotated with @MessageMapping
        // example: Greetings.greet() method is mapped to /app/hello endpoint
        registry.setApplicationDestinationPrefixes("/app");

    }
}
