package com.rdas.services.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rdas on 09/04/2015.
 */
@Service
public class BatchService {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;


}
