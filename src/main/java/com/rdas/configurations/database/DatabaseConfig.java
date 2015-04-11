package com.rdas.configurations.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * Created by rdas on 09/04/2015.
 */
@Configuration
public class DatabaseConfig {
    @Bean(name = "h2dbWithURL")
    public DataSource getH2DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // com.mysql.jdbc.Driver
        dataSource.setDriverClassName("org.h2.Driver");
        // jdbc:mysql://localhost:3306/contactdb
        dataSource.setUrl("jdbc:h2:mem:datajdbc");
        // root
        dataSource.setUsername("sa");
        // P@ssw0rd
        dataSource.setPassword("");
        DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
        return dataSource;
    }

    private DatabasePopulator createDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(new ClassPathResource("schema.sql"));
        return databasePopulator;
    }

    //TODO @Profile("dev") : Find out how this works !!!!
    @Bean(name = "embeddedDS")
    @Primary
    public DataSource dataSourceEmbeded() {
        DataSource bean = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).setName("TESTMEMDB")
                .addScript("classpath:schema.sql").build();
        return bean;
    }

    @Bean(name = "hsqldb")
    public DataSource getHsqlDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
}
