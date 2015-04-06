package com.rdas.services;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by rdas on 03/04/2015.
 *
 * This a bean constructed by AppCOnfig
 */
public class HelloWorldMessage {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldMessage.class);

    @Autowired
    private DatabaseService databaseService;


    public String sayHello() throws SQLException {
        logger.debug("executing sayHello in HelloWorldMessage.");

        logger.debug("Is table Created ? " + databaseService.isTableCreated());
        return "Hello from HelloWorldMessage(Java Configuration).";
    }
}
