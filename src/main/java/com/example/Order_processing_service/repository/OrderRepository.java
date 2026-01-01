package com.example.Order_processing_service.repository;

import com.example.Order_processing_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository {

    Optional<Order> findByStatus(String status);

}
