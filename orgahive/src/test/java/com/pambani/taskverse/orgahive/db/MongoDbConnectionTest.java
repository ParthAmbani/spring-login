package com.pambani.taskverse.orgahive.db;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MongoDbConnectionTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testMongoDbConnection() {
        // Check if the MongoTemplate bean is loaded
        assertThat(mongoTemplate).isNotNull();

        // Optionally, check if you can list collections in the database
        boolean isConnected = mongoTemplate.getDb().listCollectionNames().iterator().hasNext();
        assertThat(isConnected).isTrue();  // Passes if the database has at least one collection
    }

    @Test
    public void testMongoDbInsertAndRetrieve() {
        // Insert a sample document
        Document sample = new Document("name", "han_smith").append("email", "han.smith@example.com");
        mongoTemplate.getCollection("ticket-user").insertOne(sample);

        // Retrieve the document
        Document retrieved = mongoTemplate.getCollection("ticket-user")
                .find(new Document("name", "han_smith"))
                .first();
        // Assertions
        assertThat(retrieved).isNotNull();
        assertThat(retrieved.getString("name")).isEqualTo("han_smith");
    }


}
