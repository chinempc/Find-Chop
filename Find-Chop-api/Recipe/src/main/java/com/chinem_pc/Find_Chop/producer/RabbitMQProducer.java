package com.chinem_pc.Find_Chop.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQProducer {
    @Value("${rabbitmq.exchangeName}")
    private String exchangeName;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    private final RabbitTemplate template;

    public void sendMessage(String message) {
        log.info("Message Sent From Recipe Finder.");
        template.convertAndSend(exchangeName, routingKey, message);
    }
}
