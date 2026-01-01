package com.example.Order_processing_service.service;

import com.example.Order_processing_service.dto.OrderRequest;
import com.example.Order_processing_service.entity.Order;
import com.example.Order_processing_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(OrderRequest request) {
        Order order = new Order(); // NEW entity per request
        order.setCustomerEmail(request.getCustomerEmail());
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}

