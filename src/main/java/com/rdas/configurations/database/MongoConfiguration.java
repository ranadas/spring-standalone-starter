package com.rdas.configurations.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * Created by rdas on 12/04/2015.
 */
@Configuration
@ComponentScan
@EnableMongoRepositories
@PropertySource("classpath:mongo.properties")
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName() {
        return "spring-starter-db";
    }

    @Override
    public Mongo mongo() throws Exception {
        String url = env.getProperty("mongodb.url");
        String port = env.getProperty("mongodb.port");
        return new MongoClient(url, new Integer(port));
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.rdas.springDataMongo.model";
    }

    @Override
    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), getDatabaseName());
    }

    //@Formatter:off    
    /*
    Another way of reading property values
    @Value("${mongodb.url}")
    private String mongodbUrl;
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    */
    //@Formatter:on
}
