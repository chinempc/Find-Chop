package com.chinempc.findchopapiclickcounter.consumer;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMQConsumer {

    @Value("${rabbitmq.queueName}")
    private String queue;
}
