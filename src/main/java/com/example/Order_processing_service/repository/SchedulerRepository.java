package com.example.Order_processing_service.repository;

import com.example.Order_processing_service.entity.ScheduleConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchedulerRepository extends JpaRepository <ScheduleConfig,Long>{

    Optional<ScheduleConfig> findByTaskName(String name);

}
