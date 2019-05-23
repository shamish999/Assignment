package com.regulatory.service;

import com.regulatory.dto.StockRecord;
import com.regulatory.util.RegulatoryConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author shamish
 *
 * Class responsible for sending messages to queue
 */
@Component
public class MessageSenderService {

    private static final Logger LOG = LoggerFactory.getLogger(MessageSenderService.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    /***
     * Method to send Faulty Trader List to the queue
     * @param stockRecordSet
     */
    public void sendMessage(Set<StockRecord> stockRecordSet) {

        LOG.debug("MessageSenderService.sendMessage");

        jmsTemplate.convertAndSend(RegulatoryConstants.REGULATORY, stockRecordSet);

        //Uncomment below code if listener is ready to read from different queues
        /*for (StockRecord stockRecord : stockRecordSet) {
            String countryOfResidence = stockRecord.getCountryOfResidence();
            LOG.debug("Message Send::Country:[" + countryOfResidence + "]");
            //jmsTemplate.convertAndSend(RegulatoryConstants.REGULATORY +countryOfResidence , stockRecordSet);
        }*/
    }

}
