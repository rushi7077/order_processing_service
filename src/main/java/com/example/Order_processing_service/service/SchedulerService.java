package com.example.Order_processing_service.service;

import com.example.Order_processing_service.entity.ScheduleConfig;
import com.example.Order_processing_service.repository.SchedulerRepository;
import com.example.Order_processing_service.scheduler.OrderScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledFuture;

@Service
public class SchedulerService {

    @Autowired
    SchedulerRepository schedulerRepository;

    @Autowired
    OrderScheduler orderScheduler;

    private final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
    private ScheduledFuture<?> scheduledTask;

    public void DynamicSchedulerServic(){
        scheduler.initialize();
    }

    public void updateCronExpression(String taskName , String newCron){
        ScheduleConfig config = schedulerRepository.findByTaskName(taskName)
                .orElse(new ScheduleConfig());

        config.setTaskName(taskName);
        config.setCronExpression(newCron);
        schedulerRepository.save(config);

        restartScheduledTask(newCron);
    }

    private void restartScheduledTask(String newCron) {

        if(scheduledTask != null){
            scheduledTask.cancel(false);
        }
        scheduledTask = scheduler.schedule(orderScheduler::processPendingOrders,new CronTrigger(newCron));

    }

}
