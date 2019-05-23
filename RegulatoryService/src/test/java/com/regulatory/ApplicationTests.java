package com.regulatory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@SpringBootTest
@Component
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationTests {

        @Autowired
        ApplicationContext context;

        @Test
        public void testSpringApplicationContextLoad() throws Exception {
            Assert.assertNotNull(context);
        }

}
