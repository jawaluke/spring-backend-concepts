package org.learn.SpringBootWorkAroundBranch.controller;

import jakarta.validation.Valid;
import org.learn.SpringBootWorkAroundBranch.model.Order;
import org.learn.SpringBootWorkAroundBranch.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/order")
@RestController
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/info")
    @ResponseBody
    public String getInfo() {
        return "order service";
    }

    @PostMapping("/")
    public ResponseEntity<?> createOrder(@Valid @RequestBody Order order) {
        // 1. validate Order - Done
        if(order==null) {
            return ResponseEntity.badRequest().build();
        }
        orderService.createOrderAndSave(order);
        // 2. update order in DB
        // 3. publish request to inventory
        // 4. listen up topic to mark the order completed or failed
        return null;
    }

}
