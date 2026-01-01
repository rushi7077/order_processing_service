package com.example.Order_processing_service.scheduler;

import com.example.Order_processing_service.entity.Order;
import com.example.Order_processing_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderScheduler {

    @Autowired
    OrderRepository orderRepository;

    @Scheduled(fixedRate = 10000)
    public void processPendingOrders(){
        List<Order> orders = orderRepository.findByStatus("PENDING");
        System.out.println("processing order ");
        orders.forEach(order -> {
            order.setStatus("COMPLETED");
            orderRepository.save(order);
        });
        System.out.println("Processed pending orders "+orders.size());
    }
}
