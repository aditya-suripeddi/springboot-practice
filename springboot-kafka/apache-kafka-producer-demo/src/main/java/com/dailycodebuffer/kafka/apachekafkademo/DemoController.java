package com.dailycodebuffer.kafka.apachekafkademo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;


@RestController
public class DemoController {

    @Autowired
    @Qualifier("bookKafkaTemplate")
    private KafkaTemplate<String, Book> kafkaBookTemplate;

    @Autowired
    @Qualifier("messageKafkaTemplate")
    private KafkaTemplate<String, String> kafkaMessageTemplate;

    private static final String BOOK_TOPIC="BookTopic";
    private static final String MESSAGE_TOPIC="MessageTopic";


    @PostMapping("/publish")
    public String publishMessage(@RequestBody Book book)
    {
       kafkaBookTemplate.send(BOOK_TOPIC, book);
       return "Published Successfulyy!";
    }

    @PostMapping("/publish/{key}")
    public String publishMessage(@PathVariable String key, @RequestBody Map<String, String> input)
    {
        Optional.ofNullable(input.get(key)).map( message -> kafkaMessageTemplate.send(MESSAGE_TOPIC, message));
        return "Published Successfulyy!";
    }

}
