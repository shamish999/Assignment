package com.regulatory.util;

import com.regulatory.BaseTest;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.DestinationSource;
import org.apache.activemq.command.ActiveMQQueue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import java.util.Set;

/**
 * @author shamish
 */

public class ActiveMQTest extends BaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(ActiveMQTest.class);

    /***
     * test method to fetch all available queue names
     */
    @Test
    public void testGetQueueNames() throws Exception {

        for (ActiveMQQueue activeMQQueue : getActiveMQQueues()) {
            LOG.debug("#### :" + activeMQQueue.getQueueName());
            System.out.println(activeMQQueue.getQueueName());
        }
    }

}
