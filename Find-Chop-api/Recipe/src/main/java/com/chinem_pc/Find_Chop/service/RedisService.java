package com.chinem_pc.Find_Chop.service;

import com.chinem_pc.Find_Chop.producer.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisService {
    private final String COUNTER_KEY = "top_recipe_counter";
    @Value("${redis.threshold}")
    private int THRESHOLD;
    private final RabbitMQProducer rabbitMQProducer;

    private final RedisTemplate<String, Long> redisTemplate;

    public void incrementCounter() {
        ValueOperations<String, Long> ops = redisTemplate.opsForValue();
        Long currentCount = ops.get(COUNTER_KEY);

        if (currentCount == null) {
            currentCount = 0L;
        }

        currentCount++;

        if (currentCount >= THRESHOLD) {
            triggerUpdateTopRecipes();
            ops.set(COUNTER_KEY, 0L);
        } else {
            ops.set(COUNTER_KEY, currentCount);
        }
    }

    private void triggerUpdateTopRecipes() {
        // Send a message through RabbitMQ
        log.info("Refresh Top Recipes Triggered...");
        rabbitMQProducer.sendMessage("");
    }
}
