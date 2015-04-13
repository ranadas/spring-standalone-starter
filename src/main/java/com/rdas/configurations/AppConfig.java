package com.rdas.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.rdas.services.HelloWorldMessage;

/**
 * Created by rdas on 03/04/2015.
 */
@Configuration
@ComponentScan(basePackages = { "com.rdas.services", "com.rdas.main", "com.rdas.configurations.scheduling",
        "com.rdas.configurations.batch", "com.rdas.configurations.database",
        "com.rdas.springDataMongo.service"})
public class AppConfig {

    @Bean(name = "helloWorldMessageBean")
    public HelloWorldMessage helloWorldMessage() {
        return new HelloWorldMessage();
    }
}
