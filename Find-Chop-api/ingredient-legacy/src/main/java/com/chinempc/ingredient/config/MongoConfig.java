package com.chinempc.ingredient.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoConfig {

    @Value("${MONGO_URI}")
    private String mongoUri;

    @Value("${MONGO_DB_INGREDIENT}")
    private String mongoDb;

    @Bean
    MongoClient mongoClient() {
        return MongoClients.create("mongodb+srv://chinempc:kgRPnnDM1e650lrB@laze-menyooz.2mi90h9.mongodb.net/?retryWrites=true&w=majority&appName=Laze-Menyooz\"\n");
    }

    @Bean
    MongoOperations mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "ingredients");
    }

}
