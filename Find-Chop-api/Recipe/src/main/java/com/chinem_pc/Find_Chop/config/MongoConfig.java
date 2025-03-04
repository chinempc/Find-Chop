package com.chinem_pc.Find_Chop.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoConfig {

    @Value("${MONGO_URI}")
    private String mongoUri;

    @Value("${MONGO_DB_RECIPE}")
    private String dbName;

    @Bean
    MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }

    @Bean
    MongoOperations mongoTemplate(MongoClient mongoClient) {
        System.out.println("Using THIS DATABASE -> " + dbName);
        return new MongoTemplate(mongoClient, dbName);
    }

}
