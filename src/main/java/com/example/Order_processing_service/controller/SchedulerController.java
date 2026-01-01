package com.example.Order_processing_service.controller;

import com.example.Order_processing_service.dto.CronUpdateRequest;
import com.example.Order_processing_service.repository.SchedulerRepository;
import com.example.Order_processing_service.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    SchedulerRepository schedulerRepository;

    @PostMapping("update_cron")
    public ResponseEntity<String> updateCron(@RequestBody CronUpdateRequest request){

        schedulerService.updateCronExpression(request.getTaskName(), request.getCronExpression());
        return ResponseEntity.ok("Cron expression updated successfully");
    }

}
