package com.zxy.high.orders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


/**
 * 单元测试基类
 * @author yang.zhou
 * @date 2018/8/14
 */
@ContextConfiguration(locations = "classpath:META-INF/spring/dubbo-high-orders-trade-consumer.xml")
public class BaseTestCase extends AbstractTestNGSpringContextTests{

    private static final Logger logger = LoggerFactory.getLogger(BaseTestCase.class.getName());

    @BeforeClass
    public void setUpClass(){
        logger.info("===========Execution unit tests============");
    }

    @AfterClass
    public void tearDownClass(){
        logger.info("===========End of unit test============");
    }
}
