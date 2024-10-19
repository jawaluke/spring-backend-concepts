package org.learn.SpringBootWorkAroundBranch.controller;

import org.learn.SpringBootWorkAroundBranch.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/order")
@RestController
public class OrderController {

    @GetMapping("/info")
    @ResponseBody
    public String getInfo() {
        return "order service";
    }

    @PostMapping("/")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        // 1. validate Order
        // 2. update order in DB
        // 3. publish request to inventory
        // 4. listen up topic to get the order
    }

}
