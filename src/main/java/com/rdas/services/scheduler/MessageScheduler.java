package com.rdas.services.scheduler;

import com.rdas.springDataMongo.model.User;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by rdas on 07/04/2015.
 */
@Component
public class MessageScheduler {

    private static final Logger logger = LoggerFactory.getLogger(MessageScheduler.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Executed every 5 secs and prints the date time.
     */
    @Scheduled(fixedRate = 5000)
    public void printMessage() {
        DateTime dateTime = new DateTime();

        logger.debug("-->>Called by Spring scheduler at -> {}:{}:{} <<---", dateTime.hourOfDay().getAsShortText(), dateTime.minuteOfHour()
                .getAsShortText(), dateTime.secondOfMinute().getAsShortText());
    }
}
