package com.sms.job;

import com.sms.cache.Cache;
import com.sms.dto.StockRecord;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author shamish
 */
@Component
public class PopulateRecordProcessor implements ItemProcessor<StockRecord, StockRecord> {


    @Override
    public StockRecord process(StockRecord record) throws Exception {
        Cache.getMultiSet().add(record);
        return record;
    }
}
