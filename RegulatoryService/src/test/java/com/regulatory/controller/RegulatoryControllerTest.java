package com.regulatory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.regulatory.BaseTest;
import com.regulatory.dto.StockRecord;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author shamish
 */
@ContextConfiguration(classes = {RegulatoryControllerTest.class })
@WebAppConfiguration
@AutoConfigureMockMvc
@EnableWebMvc
public class RegulatoryControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void acceptFaultyTrader() throws Exception {

        Set<StockRecord> recordSet = new HashSet<>();
        StockRecord s = new StockRecord();
        s.setAmount(32423);
        s.setTraderId("123");
        s.setStockId("123");
        s.setCountryOfResidence("IN");
        s.setCurrency("INR");
        s.setBuyOrSell("Buy");
        s.setDob(Calendar.getInstance());
        s.setFirstName("Abc");
        s.setLastName("Pqr");
        s.setTransTime(Calendar.getInstance());
        s.setNationality("India");
        recordSet.add(s);

        String jsonString=objectMapper.writeValueAsString(recordSet);


        this.mockMvc
                .perform(post("/Regulatory/reportTrader")
                        .content(jsonString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


}