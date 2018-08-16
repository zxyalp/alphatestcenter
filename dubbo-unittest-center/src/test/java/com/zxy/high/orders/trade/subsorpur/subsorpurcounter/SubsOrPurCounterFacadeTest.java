package com.zxy.high.orders.trade.subsorpur.subsorpurcounter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zxy.high.orders.BaseTestCase;
import com.zxy.utils.FileExtUtils;
import com.zxy.utils.RandomExtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurcounter.SubsOrPurCounterFacade;
import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurcounter.SubsOrPurCounterRequest;
import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurcounter.SubsOrPurCounterResponse;

import java.io.IOException;


/**
 * 柜台购买下单接口
 *
 * @author yang.zhou
 * @date 2018/8/16
 */
public class SubsOrPurCounterFacadeTest extends BaseTestCase {

    public static final Logger logger = LoggerFactory.getLogger(SubsOrPurCounterFacadeTest.class.getName());

    @Autowired
    private SubsOrPurCounterFacade subsOrPurCounterFacade;

    private JSONObject jsonObject;


    @BeforeMethod
    public void setUp() throws IOException {

        logger.info(">>>>>>开始执行单元测试方法");

        String src = FileExtUtils.readFileToString("classpath:high/orders/trade/SubsOrPurCounterRequest.json");

        jsonObject = JSON.parseObject(src);

        jsonObject.put("appointmentDealNo", RandomExtUtils.randomNumeric(9));
        jsonObject.put("dataTrack", RandomExtUtils.randomAlphabetic(32));
        jsonObject.put("externalDealNo", RandomExtUtils.randomAlphabetic(36));

        logger.info("预约订单号：{}", jsonObject.getString("appointmentDealNo"));

    }
    
    @AfterMethod
    public void tearDown() throws Exception {
        logger.info(">>>>>>单元测试结束");
    }

    @Test(enabled = true, description = "高端柜台正常下单")
    public void web_facade_test() throws Exception {

        logger.info("json：{}", JSON.toJSON(jsonObject.toJSONString()).toString());

        SubsOrPurCounterRequest request = JSON.parseObject(jsonObject.toJSONString(), SubsOrPurCounterRequest.class);

        logger.info("请求内容：{}", JSON.toJSON(request).toString());

        SubsOrPurCounterResponse response = subsOrPurCounterFacade.execute(request);

        logger.info("返回结果：{}", JSON.toJSONString(response));

        if ("Z0000000".equals(response.getReturnCode())) {
            logger.info("测试通过,{}:{}", response.getReturnCode(), response.getDescription());
        } else {
            logger.error("测试失败,{}:{}", response.getReturnCode(), response.getDescription());
        }

        Assert.assertEquals(response.getReturnCode(), "Z0000000");

    }
    
}
