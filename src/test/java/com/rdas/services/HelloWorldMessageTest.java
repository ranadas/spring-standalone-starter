package com.rdas.services;


import static org.fest.assertions.Assertions.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rdas.configurations.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class HelloWorldMessageTest {

    @Autowired
    private HelloWorldMessage helloWorldMessage;

    @Test
    public void serviceCreated() {
        assertThat(helloWorldMessage).isNotNull();
    }


    @Test
    public void helloMessageReturnsString() throws SQLException {
        assertThat(helloWorldMessage.sayHello()).isNotEmpty();
    }
}