package org.learn.SpringBootWorkAroundBranch.config;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.learn.SpringBootWorkAroundBranch.listener.JmsPaymentProcessListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import java.util.ArrayList;
import java.util.Arrays;

import static org.learn.SpringBootWorkAroundBranch.util.OrderUtil.PAYMENT_QUEUE;

@Configuration
@EnableJms
public class JmsActiveMqConfig {

    @Autowired
    private JmsPaymentProcessListener jmsPaymentProcessListener;

    @Bean
    public ConnectionFactory connectionFactory1() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://localhost:61616");
        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setTrustedPackages(Arrays.asList("org.learn.SpringBootWorkAroundBranch.entity", "org.learn.SpringBootWorkAroundBranch.enums", "java.util"));
        return new CachingConnectionFactory(connectionFactory);
    }

    @Bean(name = "paymentjmstemplate")
    public JmsTemplate jmsTemplate1(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestinationName(PAYMENT_QUEUE);
        return jmsTemplate;
    }

    @Bean
    public DefaultMessageListenerContainer defaultMessageListenerContainer(ConnectionFactory connectionFactory) {
        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setDestinationName(PAYMENT_QUEUE);
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
        defaultMessageListenerContainer.setPubSubDomain(false);
        defaultMessageListenerContainer.setMessageListener(jmsPaymentProcessListener);
        return defaultMessageListenerContainer;
    }

}
