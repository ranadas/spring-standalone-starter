package com.rdas.services;

import org.springframework.stereotype.Component;

/**
 * As this is annotated as component, not need to new this in config class. Just autowire :
 * <code>
 * @Autowired
 * AlternativeHelloWorldBean helloWorldMessage;
 * </code>
 * Created by rdas on 06/04/2015.
 */
@Component
public class AlternativeHelloWorldBean {

    public String hello() {
        return "Hello from AlternativeHelloWorldBean(Java Configuration).";
    }
}
