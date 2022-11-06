package com.dailycodebuffer.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;


/*

        WebSocket is a thin, lightweight layer above TCP.
        This makes it suitable for using “subprotocols” to embed messages.
        In this guide, we use STOMP messaging with Spring to create an interactive web application.
        STOMP is a subprotocol operating on top of the lower-level WebSocket.

 */


@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")  // broadcast Greeting POJO returned to all subscribers of /topic/greetings
    public Greeting greet(HelloMessage message) throws InterruptedException {

        // simulates a processing delay by causing the
        // thread to sleep for one second
        //
        // This is to demonstrate that, after the client sends a message,
        // the server can take as long as it needs to asynchronously process the message.
        Thread.sleep(2000);


        // input message is sanitized, since, in this case, it will
        // be echoed back and re-rendered in the browser DOM on the client side.
        return new Greeting("Hello, " +
                HtmlUtils.htmlEscape(message.getName()));
    }
}
