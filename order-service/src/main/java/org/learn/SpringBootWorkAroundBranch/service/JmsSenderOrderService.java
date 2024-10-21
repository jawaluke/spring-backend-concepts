package org.learn.SpringBootWorkAroundBranch.service;

import org.learn.SpringBootWorkAroundBranch.entity.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import static org.learn.SpringBootWorkAroundBranch.util.OrderUtil.PAYMENT_QUEUE;

@Service
public class JmsSenderOrderService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public boolean sentOrderToPaymentService(OrderDTO orderDTO) {
        try{
            jmsTemplate.convertAndSend(PAYMENT_QUEUE , orderDTO);
            return true;
        }
        catch (Exception e) {
            System.out.println("Exception = " + e);
            return false;
        }
    }

}
