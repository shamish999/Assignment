package com.sms.cache;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.sms.dto.StockRecord;

import java.util.*;

/**
 * @author shamish
 */
public class Cache {

    private static Cache cache = null;

    private static Multiset<StockRecord> multiSet;

    private static Set<StockRecord> faultyRecordSet;

    private Cache() {}

    static{

        if (cache == null)
                cache = new Cache();
        initCache();
    }

   // public static Cache getInstance(){ return cache;}


    private static void initCache(){
        //Uncomment if number files / records is too and processing time is more than 1 minute.
        // multiSet = ConcurrentHashMultiset.create();
        multiSet = HashMultiset.create();
        faultyRecordSet = new HashSet<>();
    }

    public static void add (StockRecord stockRecord)
    {
        multiSet.add(stockRecord);
    }

    public static void flushCache ()
    {
        multiSet.clear();
    }

    public static Multiset<StockRecord> getMultiSet(){
        return multiSet;
    }

    public static Set<StockRecord> getFaultyRecordSet(){
        return faultyRecordSet;
    }


}
