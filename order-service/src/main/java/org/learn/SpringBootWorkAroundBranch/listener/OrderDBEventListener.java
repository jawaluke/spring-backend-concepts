package org.learn.SpringBootWorkAroundBranch.listener;

import org.learn.SpringBootWorkAroundBranch.entity.OrderDTO;
import org.learn.SpringBootWorkAroundBranch.model.OrderEvent;
import org.learn.SpringBootWorkAroundBranch.service.JmsSenderOrderService;
import org.learn.SpringBootWorkAroundBranch.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderDBEventListener {

    @Autowired
    private OrderService orderService;

    @Autowired
    private JmsSenderOrderService jmsSenderOrderService;

    @EventListener
    public void handleOrderCreatedEvent(OrderEvent orderEvent) {
        OrderDTO orderDTO = orderService.getOrderByOrderId(orderEvent.getOrderId());
        if(orderDTO==null) {
            throw new RuntimeException("no order is found for the order : "+orderEvent.toString());
        }
        jmsSenderOrderService.sentOrderToPaymentService(orderDTO);
    }

}
