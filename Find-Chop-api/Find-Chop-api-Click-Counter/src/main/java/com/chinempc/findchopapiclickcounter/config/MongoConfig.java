package com.chinempc.findchopapiclickcounter.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoConfig {

    //@Value("${env.MONGO_URI}")
    private String mongoUri = "mongodb+srv://chinempc:kgRPnnDM1e650lrB@laze-menyooz.2mi90h9.mongodb.net/?retryWrites=true&w=majority&appName=Laze-Menyooz";

    //@Value("${env.MONGO_DB}")
    private String dbName = "click-count";

    @Bean
    MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }

    @Bean
    MongoOperations mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, dbName);
    }

}
