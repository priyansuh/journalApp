package com.demoLearning.journalApp;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        // Explicitly connects to your local MongoDB instance
        return MongoClients.create("mongodb://localhost:27017");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        // This MANDATES that the database name is journaldb, completely bypassing auto-config
        return new MongoTemplate(mongoClient(), "journaldb");
    }
}