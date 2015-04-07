package com.rdas.configurations;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.rdas.services.HelloWorldMessage;

/**
 * Created by rdas on 03/04/2015.
 */
@Configuration
@ComponentScan(basePackages = { "com.rdas.services", "com.rdas.main" , "com.rdas.configurations.scheduling"})
public class AppConfig {

    @Bean(name = "helloWorldMessageBean")
    public HelloWorldMessage helloWorldMessage() {
        return new HelloWorldMessage();
    }

    @Bean(name = "dbWithURL")
    public DataSource getDataSourceOld() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // com.mysql.jdbc.Driver
        dataSource.setDriverClassName("org.h2.Driver");
        // jdbc:mysql://localhost:3306/contactdb
        dataSource.setUrl("jdbc:h2:mem:datajdbc");
        // root
        dataSource.setUsername("sa");
        // P@ssw0rd
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean(name = "embeddedDS")
    public DataSource dataSourceEmbeded() {
        DataSource bean = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).setName("TESTMEMDB")
                .addScript("classpath:schema.sql").build();
        return bean;
    }

}
