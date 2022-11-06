package com.dailycodebuffer.kafka.consumer;

import com.dailycodebuffer.kafka.Book;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "BookTopic", groupId = "Amazon", containerFactory = "bookKafkaListenerContainerFactoryGroupAmazon")
    public void consumeBookJsonGroupJson(Book book)
    {
        // Consumes books to be added to Amazon Catalog
        String message = String.format("Topic:BookTopic, GroupId:groupAmazon, Data: Book(%s, %s)", book.getBookName(), book.getIsbn());
        System.out.println(message);
    }

    @KafkaListener(topics = "BookTopic", groupId = "Audible", containerFactory = "bookKafkaListenerContainerFactoryGroupAudible")
    public void consumeBookJsonGroupRead(Book book)
    {
        // Consumes books to be added to Audible catalog
        String message = String.format("Topic:BookTopic, GroupId:groupAudible. Data: Book(%s, %s)", book.getBookName(), book.getIsbn());
        System.out.println(message);
    }

    @KafkaListener(topics = "MessageTopic", groupId = "Inbox", containerFactory = "textKafkaListenerContainerFactoryGroupInbox")
    public void consumeMessage(String message)
    {
        // consumes messages sent to inbox
        System.out.println( "Topic:MessageTopic, GroupId:Inbox, Data:" + message);
    }
}
