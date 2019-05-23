package com.sms;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableScheduling
@EnableJms
//public class Application  implements CommandLineRunner{
public class Application{

    @Autowired
    private JobLauncher jobLauncher;

   /* @Autowired
    private SMSJobRepository jobRepository;*/

    @Autowired
    @Qualifier("readRecordsJob")
    private Job readRecordsJob;


    @Autowired
    @Qualifier("regulatoryReportingJob")
    private Job regulatoryReportingJob;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Scheduled(cron = "0 */2 * * * ?")
    public void readStockRecords() throws Exception
    {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(readRecordsJob, params);
    }

    @Scheduled(cron = "0 */10 * * * ?")
    public void reportFaultyTraders() throws Exception
    {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(regulatoryReportingJob, params);
    }


    /*@Override
    public void run(String... args) throws Exception {
        jobRepository.buildAndScheduleJob();
    }*/
}
