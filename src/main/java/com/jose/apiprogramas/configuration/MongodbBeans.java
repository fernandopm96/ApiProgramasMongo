package com.jose.apiprogramas.configuration;

import com.jose.apiprogramas.entities.Program;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
})
public class MongodbBeans {

    //Dependency with the global collection, so we can inject it throughout all our program
    @Bean
    public MongoCollection<Program> getMongoDatabase(){
        CodecRegistry pojoCodecRegistry =
                CodecRegistries
                        .fromRegistries(
                                MongoClient
                                        .getDefaultCodecRegistry(), // Default codec
                                CodecRegistries                     //customCodecProvider
                                        .fromProviders(
                                                PojoCodecProvider
                                                        .builder()
                                                        .automatic(true)
                                                        .build()));
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("crysec").withCodecRegistry(pojoCodecRegistry);
        MongoCollection<Program> collection = db.getCollection("programs", Program.class);
        collection.createIndex(Indexes.ascending("name"), new IndexOptions().unique(true));


        return collection;
    }
}
