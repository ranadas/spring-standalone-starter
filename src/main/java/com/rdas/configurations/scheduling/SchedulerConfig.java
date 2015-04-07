package com.rdas.configurations.scheduling;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

//@Formatter:off
/**
 * By @EnableScheduling we are telling the application we have scheduling.
 * This will find out any @Scheduled methods.
 *
 * In case have tasks which can take long time to complete, and are frequent, we can optionally
 * configure thread-pool with specified pool-size to handle each tasks in separate thread.
 *
 * Created by rdas on 07/04/2015.
 */
//@Formatter:on
@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {
    /*
     * @Bean public MessageScheduler bean() { return new MessageScheduler(); }
     */

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }
}
