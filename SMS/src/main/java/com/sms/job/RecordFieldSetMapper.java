package com.sms.job;

import com.sms.dto.StockRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author shamish
 */
public class RecordFieldSetMapper implements FieldSetMapper<StockRecord> {

    private static final Logger LOG = LoggerFactory.getLogger(RecordFieldSetMapper.class);

    //maps file fields to StockRecord Object
    public StockRecord mapFieldSet(FieldSet fieldSet) {

        StockRecord stockRecord = new StockRecord();
        stockRecord.setNationality(fieldSet.readRawString(0));
        stockRecord.setCountryOfResidence(fieldSet.readRawString(1));
        String dobString = fieldSet.readRawString(2);
        Calendar dobCal = null;
        try {
            dobCal = getDobCalendar(dobString);
        } catch (ParseException e) {
            LOG.error("Error parsing date" + dobString);
        }
        stockRecord.setDob(dobCal);
        stockRecord.setTraderId(fieldSet.readRawString(3));
        stockRecord.setAmount(fieldSet.readDouble(4));
        stockRecord.setCurrency(fieldSet.readRawString(5));
        stockRecord.setStockId(fieldSet.readRawString(6));
        stockRecord.setBuyOrSell(fieldSet.readRawString(7));
        stockRecord.setFirstName(fieldSet.readRawString(8));
        stockRecord.setLastName(fieldSet.readRawString(9));
        return stockRecord;
    }

    // Convert string to calendar date
    private Calendar getDobCalendar(String dobString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        Date dobDate = formatter.parse(dobString);
        Calendar dobCal = Calendar.getInstance();
        dobCal.setTime(dobDate);
        return dobCal;
    }
}
