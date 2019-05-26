package hr.java.web.vesliga.moneyapp.config;

import hr.java.web.vesliga.moneyapp.jobs.StatisticsJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail expenseJobDetail(){
        return JobBuilder.newJob(StatisticsJob.class).withIdentity("expenseJob").storeDurably().build();
    }

    @Bean
    public Trigger expenseJobTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever();

        return TriggerBuilder.newTrigger().forJob(expenseJobDetail()).withIdentity("expenseTrigger").withSchedule(scheduleBuilder).build();
    }
}
