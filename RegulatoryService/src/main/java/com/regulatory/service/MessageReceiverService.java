package com.regulatory.service;

import com.regulatory.dto.StockRecord;
import com.regulatory.util.RegulatoryConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.jms.listener.AbstractMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.List;
import java.util.Set;

/**
 * @author shamish
 *
 * Listener class receives and logs messages to regulatory.log file
 */
@Component
public class MessageReceiverService {

    private static final Logger LOG = LoggerFactory.getLogger(MessageReceiverService.class);

    /***
     * method to receive message from queue
     * @param stockRecordSet
     */
    @JmsListener(destination = RegulatoryConstants.REGULATORY)
    public void receiveMessage(Set<StockRecord> stockRecordSet) {

        LOG.debug("Message Received :: " + stockRecordSet);
    }
}
