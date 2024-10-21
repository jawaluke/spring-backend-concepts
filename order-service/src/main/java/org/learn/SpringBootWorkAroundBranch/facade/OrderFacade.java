package org.learn.SpringBootWorkAroundBranch.facade;

import org.learn.SpringBootWorkAroundBranch.entity.OrderDTO;
import org.learn.SpringBootWorkAroundBranch.mapper.OrderMapper;
import org.learn.SpringBootWorkAroundBranch.model.Order;
import org.learn.SpringBootWorkAroundBranch.model.OrderEvent;
import org.learn.SpringBootWorkAroundBranch.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class OrderFacade {

    private OrderMapper orderMapper;
    private OrderService orderService;
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    public OrderFacade(ApplicationEventPublisher eventPublisher, OrderMapper orderMapper, OrderService orderService) {
        this.eventPublisher = eventPublisher;
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }

    @Transactional
    public void processOrdertoPayment(Order order) {
        // 1. convert order to orderDTO
        OrderDTO orderDTO = orderMapper.toOrderDTO(order);
        // 2. saved to DB
        orderService.createAndSaveOrder(orderDTO);
        // 3. callback after commit publish event to
        registerTransactionCallbackForOrderPaymentQueue(orderDTO.getOrderId());
    }

    private void registerTransactionCallbackForOrderPaymentQueue(long orderId) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                // publish application event to orderDBEvent
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                eventPublisher.publishEvent(new OrderEvent(orderId));
            }

            @Override
            public void afterCompletion(int status) {
                System.out.println("Saved to Database");
            }

            @Override
            public void beforeCommit(boolean readOnly) {
                System.out.println("Before Committing "+ readOnly);
            }
        });
    }

}
