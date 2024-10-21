package org.learn.SpringBootWorkAroundBranch.controller;

import jakarta.validation.Valid;
import org.learn.SpringBootWorkAroundBranch.entity.OrderDTO;
import org.learn.SpringBootWorkAroundBranch.facade.OrderFacade;
import org.learn.SpringBootWorkAroundBranch.model.Order;
import org.learn.SpringBootWorkAroundBranch.service.JmsSenderOrderService;
import org.learn.SpringBootWorkAroundBranch.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/order")
@RestController
public class OrderController {

    private OrderService orderService;
    private JmsSenderOrderService jmsSenderOrderService;
    private OrderFacade orderFacade;

    @Autowired
    public OrderController(JmsSenderOrderService jmsSenderOrderService, OrderFacade orderFacade, OrderService orderService) {
        this.jmsSenderOrderService = jmsSenderOrderService;
        this.orderFacade = orderFacade;
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
        orderFacade.processOrdertoPayment(order);
        return ResponseEntity.ok("success");
    }

}
