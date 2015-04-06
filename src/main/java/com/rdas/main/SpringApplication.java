package com.rdas.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.rdas.configurations.AppConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Application Entry point
 * Created by rdas on 06/04/2015.
 */
@Component
public class SpringApplication {

    @Autowired
    @Qualifier("embeddedDS")
    public void setSpiedDB(DataSource spiedDB) {
        dataSource=spiedDB;
    }

    private DataSource dataSource;

    public void sayHello() throws SQLException {
        System.out.println("\t In SpringApplication Method !");

    }

    public static void main(String[] args) throws SQLException {
        // final ApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_PATH);
        // final SpringApplication minimalSpringApp = context.getBean(SpringApplication.class);
        // minimalSpringApp.sayHello();

        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println(applicationContext.getApplicationName());

        final SpringApplication minimalSpringApp = applicationContext.getBean(SpringApplication.class);
        //final Some-Other-Bean other = applicationContext.getBean(SpringApplication.class);

        minimalSpringApp.sayHello();
    }


}
