package com.regulatory.controller;


import com.regulatory.dto.StockRecord;
import com.regulatory.service.MessageReceiverService;
import com.regulatory.service.MessageSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * @author shamish
 *
 * Controller for accepting Trader Information
 */
@RestController
@RequestMapping("/Regulatory")
public class RegulatoryController {


    private static final Logger LOG = LoggerFactory.getLogger(MessageSenderService.class);

    @Autowired
    private MessageSenderService messageSenderService;



    @RequestMapping(value = "/reportTrader",method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus acceptFaultyTrader(@Valid  @RequestBody Set<StockRecord> stockRecordSet) {

        if(CollectionUtils.isEmpty(stockRecordSet))
            throw new IllegalArgumentException("Missing required parameters values in field stockRecordSet");

        LOG.debug("RegulatoryController.acceptFaultyTrader() :tradeMapCount :"+stockRecordSet);

        messageSenderService.sendMessage(stockRecordSet);

        return HttpStatus.OK;
    }
}


