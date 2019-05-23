package com.regulatory;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.DestinationSource;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.JMSException;
import java.util.Set;

/**
 * @author shamish
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = "com.regulatory")
public class BaseTest {

    @Test
    void testBase() {
    }
    // Method to get list of queue names
    public Set<ActiveMQQueue> getActiveMQQueues() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        ActiveMQConnection connection = (ActiveMQConnection) connectionFactory.createConnection();

        connection.start();
        DestinationSource ds = connection.getDestinationSource();

        connection.start();

        return ds.getQueues();
    }


}
