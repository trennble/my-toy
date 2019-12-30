package com.trennble.quartz;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzTest {

    public static void main(String[] args) {
        Scheduler scheduler=null;
        try {
            // Grab the Scheduler instance from the Factory
            scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();
            // define the job and tie it to our HelloJob class
            JobDetail jobA = newJob(GroupA.class)
                    .withIdentity("job1", "groupa")
                    .build();
            // define the job and tie it to our HelloJob class
            JobDetail jobB = newJob(GroupB.class)
                    .withIdentity("job1", "groupb")
                    .build();

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "groupa")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(1)
                            .repeatForever())
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(jobA, trigger);
            // thrown org.quartz.SchedulerException: Trigger does not reference given job!
            scheduler.scheduleJob(jobB, trigger);

        } catch (SchedulerException se) {
            se.printStackTrace();
        }finally {
            if (scheduler!=null){
                try {
                    Thread.sleep(10000);
                    scheduler.shutdown();
                } catch (SchedulerException | InterruptedException se) {
                    se.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testForJob() {
        Scheduler scheduler=null;
        try {
            // Grab the Scheduler instance from the Factory
            scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();
            // define the job and tie it to our HelloJob class
            JobDetail jobA = newJob(GroupA.class)
                    .withIdentity("job1", "groupa")
                    .build();
            // define the job and tie it to our HelloJob class
            JobDetail jobB = newJob(GroupB.class)
                    .withIdentity("job1", "groupb")
                    .build();

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "groupa")
                    .forJob(jobB)
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(1)
                            .repeatForever())
                    .build();

            // thrown org.quartz.SchedulerException: Trigger does not reference given job!
            // scheduler.scheduleJob(jobA, trigger);
            // org.quartz.JobPersistenceException: The job (groupb.job1) referenced by the trigger does not exist.
            // scheduler.scheduleJob(trigger);
            scheduler.scheduleJob(jobB,trigger);

        } catch (SchedulerException se) {
            se.printStackTrace();
        }finally {
            if (scheduler!=null){
                try {
                    Thread.sleep(10000);
                    scheduler.shutdown();
                } catch (SchedulerException | InterruptedException se) {
                    se.printStackTrace();
                }
            }
        }
    }

    public static class GroupB implements Job {
        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("hello b");
        }
    }

    public static class GroupA implements Job {
        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("hello a");
        }
    }
}