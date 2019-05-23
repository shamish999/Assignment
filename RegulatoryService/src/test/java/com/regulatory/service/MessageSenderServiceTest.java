package com.regulatory.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.regulatory.BaseTest;
import com.regulatory.dto.StockRecord;
import com.regulatory.util.RegulatoryConstants;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.DestinationSource;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;


/**
 * @author shamish
 */
public class MessageSenderServiceTest extends BaseTest {


    @Autowired
    private MessageSenderService sender;

    @Autowired MessageReceiverService receiver;

    @Test
    public void testReceive() throws Exception {

        Set<StockRecord> recordSet = new HashSet<>();
        StockRecord s = new StockRecord();
        s.setAmount(32423);
        s.setTraderId("123");
        s.setStockId("456");
        s.setCountryOfResidence("IN");
        s.setCurrency("INR");
        s.setBuyOrSell("Buy");
        s.setDob(Calendar.getInstance());
        s.setFirstName("Abc");
        s.setLastName("Pqr");
        s.setTransTime(Calendar.getInstance());
        s.setNationality("India");
        recordSet.add(s);

        sender.sendMessage(recordSet);

       for (ActiveMQQueue activeMQQueue : getActiveMQQueues()) {
           Assert.assertTrue(activeMQQueue.getQueueName().equals("Regulatory"));
        }
    }
}