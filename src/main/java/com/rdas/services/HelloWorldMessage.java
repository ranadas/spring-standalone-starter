package com.rdas.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rdas on 03/04/2015.
 *
 * This a bean constructed by AppCOnfig
 */
public class HelloWorldMessage {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldMessage.class);

    public String sayHello() {
        logger.debug("executing sayHello in HelloWorldMessage.");
        return "Hello from HelloWorldMessage(Java Configuration).";
    }
}
