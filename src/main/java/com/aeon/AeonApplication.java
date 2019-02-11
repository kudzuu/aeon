package com.aeon;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AeonApplication {

    public static void main(String[] args) throws BeansException, JobExecutionAlreadyRunningException, JobRestartException, InterruptedException, JobParametersInvalidException, JobInstanceAlreadyCompleteException {
        Log log = LogFactory.getLog(AeonApplication.class);

        SpringApplication app = new SpringApplication(AeonApplication.class);
        ConfigurableApplicationContext ctx = app.run(args);

        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
        JobParameters jobParameters = new JobParametersBuilder().addDate("date", new Date()).toJobParameters();

        Job job = ctx.getBean("sourceFlightJob", Job.class);

        JobExecution jobExecution = jobLauncher.run(job, jobParameters);

        BatchStatus batchStatus = jobExecution.getStatus();

        while(batchStatus.isRunning()) {
            log.info("********** Running... **********");
            Thread.sleep(1000);
        }

        ExitStatus exitStatus = jobExecution.getExitStatus();
        String exitCode = exitStatus.getExitCode();

        log.info(String.format("********** Exit status: %s", exitCode));

        ctx.close();
    }

}

