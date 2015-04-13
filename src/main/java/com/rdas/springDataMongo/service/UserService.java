package com.rdas.springDataMongo.service;

import java.util.List;

import javax.annotation.PostConstruct;

import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.rdas.springDataMongo.model.User;

/**
 * Created by rdas on 12/04/2015.
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    @Qualifier("mongoTemplate")
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void initIt() throws Exception {
        dropPersonCollection();
        createPersonCollection();

        for (int i = 0; i < 1000; i++) {
            // double age = Math.ceil(Math.random() * 100);
            User user = new User(i, "Rana", "Das", 20, "rdas@email.com");
            mongoTemplate.save(user);
        }
    }

    public void isDbValid() {
        logger.debug(mongoTemplate.getDb().toString());

        double number = Math.ceil(Math.random() * 1000);
        Query searchUserQuery = new Query(Criteria.where("number").is(number));
        User savedUser = mongoTemplate.findOne(searchUserQuery, User.class);
        logger.info("Finding user {}.", searchUserQuery.toString());
        logger.info("savedUser : {}." + savedUser);

        List<User> results = mongoTemplate.findAll(User.class);
        logger.info("Total number in database: {}", results.size());

    }

    /**
     * this will create a {@link User} collection if the collection does not already exists
     */
    public void createPersonCollection() {
        if (!mongoTemplate.collectionExists(User.class)) {
            mongoTemplate.createCollection(User.class);
        }
    }

    /**
     * this will drop the {@link User} collection if the collection does already exists
     */
    public void dropPersonCollection() {
        if (mongoTemplate.collectionExists(User.class)) {
            mongoTemplate.dropCollection(User.class);
        }
    }
}
