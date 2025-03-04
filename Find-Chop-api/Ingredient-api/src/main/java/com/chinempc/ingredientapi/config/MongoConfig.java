package com.chinempc.ingredientapi.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Value("${MONGO_URI}")
    private String mongoUri;

    @Value("${MONGO_DB_INGREDIENT}")
    private String dbName;

    @Bean
    MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }

    @Bean
    MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, dbName);
    }
}
