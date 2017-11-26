package ru.navigator.mall_navigator;

import org.springframework.test.context.ContextConfiguration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations={"classpath:applicationContext.xml"} )
public class MainTest {
    private static final Logger LOG = LoggerFactory.getLogger(MainTest.class);

    @Test
    public void runTest() {
        LOG.info("Test");
    }
}
