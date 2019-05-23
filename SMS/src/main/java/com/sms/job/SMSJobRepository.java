package com.sms.job;

import com.sms.factory.SMSJobFactory;
import com.sms.util.SMSConstants;
import org.apache.activemq.broker.scheduler.JobScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.ReferenceJobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author shamish
 */
public class SMSJobRepository {

    private static final Logger LOG = LoggerFactory.getLogger(SMSJobRepository.class);

    @Autowired
    private JobScheduler jobScheduler;

    @Autowired
    private SMSJobFactory smsJobFactory;

    @Autowired
    private JobRegistry jobRegistry;

    @Value("${read.csv.job.enabled}")
    private boolean isReadCSVActivated;

    @Value("${read.csv.cron}")
    private String readCSVJobCron;

    @Value("${regulatory.report.job.enabled}")
    private boolean isRRActivated;

    public void buildAndScheduleJob() throws DuplicateJobException {

        Job job;
        if(isReadCSVActivated){
            job = smsJobFactory.createJob(SMSConstants.READ_CSV);
            jobRegistry.register(new ReferenceJobFactory(job));
            //jobScheduler.scheduleJob(job.getName(),"SMSConstants.READ_CSV secheduled",new CronreadCSVJobCron);

        }

    }
}
