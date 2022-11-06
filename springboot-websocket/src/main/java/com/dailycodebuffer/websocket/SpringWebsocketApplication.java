package com.dailycodebuffer.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*

        WebSocket is a thin, lightweight layer above TCP.
        This makes it suitable for using “subprotocols” to embed messages.
        In this guide, we use STOMP messaging with Spring to create an interactive web application.
        STOMP is a subprotocol operating on top of the lower-level WebSocket.

 */


@SpringBootApplication
public class SpringWebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebsocketApplication.class, args);
	}

}
