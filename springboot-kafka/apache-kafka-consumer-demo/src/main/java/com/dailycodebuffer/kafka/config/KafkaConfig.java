package com.dailycodebuffer.kafka.config;


import com.dailycodebuffer.kafka.Book;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public ConsumerFactory<String, Book> bookConsumerFactoryAmazon() {
        Map<String, Object> config = new HashMap<>();

        // TODO: 05/02/22  Add comments to explain the config settings below 
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "Amazon");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // error: compiler error jackon dependency not found
        //
        // solution:
        //
        //  add following in pom.xml
        //          <dependency>
        //			    <groupId>com.fasterxml.jackson.core</groupId>
        //			    <artifactId>jackson-databind</artifactId>
        //		    </dependency>
        //
        //
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        // error: MessageConversionException: Caused by  Book.class not found/resolved
        // solution:  added code in [[ ]] -> JsonDeserializer<>(Book.class[[, useHeadersifPresent:false]]);
        // reference: https://stackoverflow.com/questions/54690518/spring-kafka-jsondesirialization-messageconversionexception-failed-to-resolve-cl
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(Book.class, false));
    }

    @Bean
    public ConsumerFactory<String, Book> bookConsumerFactoryAudible() {
        Map<String, Object> config = new HashMap<>();

        // TODO: 05/02/22  Add comments to explain the config settings below
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "Audible");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // error: compiler error jackon dependency not found
        //
        // solution:
        //
        //  add following in pom.xml
        //          <dependency>
        //			    <groupId>com.fasterxml.jackson.core</groupId>
        //			    <artifactId>jackson-databind</artifactId>
        //		    </dependency>
        //
        //
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);


        // error: MessageConversionException: Caused by  Book.class not found/resolved
        // solution:  added code in [[ ]] -> JsonDeserializer<>(Book.class[[, useHeadersifPresent:false]]);
        // reference: https://stackoverflow.com/questions/54690518/spring-kafka-jsondesirialization-messageconversionexception-failed-to-resolve-cl
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(Book.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory bookKafkaListenerContainerFactoryGroupAmazon()
    {
        ConcurrentKafkaListenerContainerFactory<String, Book> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(bookConsumerFactoryAmazon());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory bookKafkaListenerContainerFactoryGroupAudible()
    {
        ConcurrentKafkaListenerContainerFactory<String, Book> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(bookConsumerFactoryAudible());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, String> stringConsumerFactory()
    {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "Mail");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new StringDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory textKafkaListenerContainerFactoryGroupInbox()
    {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stringConsumerFactory());
        return factory;
    }
}
