package org.learn.SpringBootWorkAroundBranch.listener;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import org.learn.SpringBootWorkAroundBranch.entity.OrderDTO;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static org.learn.SpringBootWorkAroundBranch.util.OrderUtil.PAYMENT_QUEUE;

@Component
public class JmsPaymentProcessListener implements MessageListener {

    @Override
    @JmsListener(destination = PAYMENT_QUEUE)
    public void onMessage(Message message) {
        System.out.println("message = " + message.toString());
        if(message instanceof ObjectMessage) {
            ObjectMessage objectMessage = (ObjectMessage) message;
            try {
                OrderDTO orderDTO = (OrderDTO) objectMessage.getObject();
                System.out.println("orderDTO = " + orderDTO);
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
