package org.learn.SpringBootWorkAroundBranch.config;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JmsActiveMqConfig {

    private final String paymentQueueName = "PaymentProgressQueue";

    @Bean
    public ConnectionFactory connectionFactory1() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://localhost:61616");
        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("admin");
        return new CachingConnectionFactory(connectionFactory);
    }

    @Bean(name = "paymentjmstemplate")
    public JmsTemplate jmsTemplate1(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestinationName(paymentQueueName);
        return jmsTemplate;
    }

}
