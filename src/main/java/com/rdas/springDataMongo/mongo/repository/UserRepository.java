package com.rdas.springDataMongo.mongo.repository;

import com.rdas.springDataMongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rdas on 12/04/2015.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
