package com.rdas.configurations.scheduling;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * By @EnableScheduling we are telling the application we have scheduling.
 * This will find out any @Scheduled methods.
 * Created by rdas on 07/04/2015.
 */
@Configuration
@EnableScheduling
public class SchedulerConfig {
    /*
    @Bean
    public MessageScheduler bean() {
        return new MessageScheduler();
    }
     */
}
