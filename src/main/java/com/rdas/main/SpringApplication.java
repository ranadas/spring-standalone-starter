package com.rdas.main;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.rdas.configurations.AppConfig;
import com.rdas.services.HelloWorldMessage;
import com.rdas.springDataMongo.service.UserService;

/**
 * Application Entry point. This Doesn't have to be a component as such.
 * It is now so that we can get the bean to execute the sample method, sayHello().
 * Created by rdas on 06/04/2015.
 */
@Component
public class SpringApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringApplication.class);

    @Autowired
    private HelloWorldMessage helloWorldMessage;

    @Autowired
    private UserService userService;

    public void sayHello() throws SQLException {
        logger.debug("\t In SpringApplication sayHello Method !");
        logger.info(helloWorldMessage.sayHello());
        userService.isDbValid();
    }

    public static void main(String[] args) throws SQLException {
        // final ApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_PATH);
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        logger.info(applicationContext.getApplicationName());
        //applicationContext.getEnvironment().setActiveProfiles("dev");
        //applicationContext.scan("com.city81");

        final SpringApplication minimalSpringApp = applicationContext.getBean(SpringApplication.class);
        //final Some-Other-Bean other = applicationContext.getBean(SpringApplication.class);

        minimalSpringApp.sayHello();
    }


}
