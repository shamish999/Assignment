package com.regulatory.service;

import com.regulatory.util.RegulatoryConstants;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.DestinationSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.Enumeration;

/**
 * @author shamish
 */
public class MQBrowser {

    private static final Logger LOG = LoggerFactory.getLogger(MQBrowser.class);

    public void browseMessages() throws Exception{

        Enumeration<?> messagesInQueue = getMessagesFromQueue(RegulatoryConstants.REGULATORY);

        while (messagesInQueue.hasMoreElements()) {
            Message queueMessage = (Message) messagesInQueue.nextElement();
            LOG.debug(queueMessage.toString());
        }
    }

    public static Enumeration<?> getMessagesFromQueue(String destination) throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(RegulatoryConstants.MQ_URL);
        ActiveMQConnection connection = (ActiveMQConnection)connectionFactory.createConnection();
        DestinationSource ds = connection.getDestinationSource();

        QueueSession queueSession = connection.createQueueSession(true, Session.CLIENT_ACKNOWLEDGE);
        Queue queue = queueSession.createQueue(destination);
        QueueBrowser browser = queueSession.createBrowser(queue);
        return (Enumeration<?>) browser.getEnumeration();
    }
}
