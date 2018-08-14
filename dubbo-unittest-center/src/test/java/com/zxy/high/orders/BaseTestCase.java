package com.zxy.high.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurweb.SubsOrPurWebFacade;
import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurweb.SubsOrPurWebResponse;
import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurweb.SubsOrPurWebRequest;



/**
 * @author yang.zhou
 * @date 2018/8/14
 */
@ContextConfiguration(locations = "classpath:META-INF/spring/dubbo-high-orders-trade-consumer.xml")
public class BaseTestCase extends AbstractTestNGSpringContextTests{


}
