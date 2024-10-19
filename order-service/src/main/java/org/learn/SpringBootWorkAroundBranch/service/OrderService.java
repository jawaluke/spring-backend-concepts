package org.learn.SpringBootWorkAroundBranch.service;

import jakarta.validation.Valid;
import org.learn.SpringBootWorkAroundBranch.entity.OrderDTO;
import org.learn.SpringBootWorkAroundBranch.mapper.OrderMapper;
import org.learn.SpringBootWorkAroundBranch.model.Order;
import org.learn.SpringBootWorkAroundBranch.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public void createOrderAndSave(@Valid Order order) {
        OrderDTO orderDTO = orderMapper.toOrderDTO(order);
        orderRepository.save(orderDTO);
    }
}
