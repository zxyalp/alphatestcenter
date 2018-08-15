package com.zxy.high.orders.trade;


import com.alibaba.fastjson.JSONObject;
import com.zxy.high.orders.BaseTestCase;
import com.zxy.utils.FileExtUtils;
import com.zxy.utils.RandomExtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurweb.SubsOrPurWebFacade;
import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurweb.SubsOrPurWebRequest;
import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurweb.SubsOrPurWebResponse;
import org.testng.annotations.*;
import org.testng.Assert;

import java.io.IOException;

import com.alibaba.fastjson.JSON;


/**
 * 高端网站下单接口
 * 接口路径：com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurweb.SubsOrPurWebFacade;
 * @author yang.zhou
 * @date 2018/8/14
 */
public class SubsOrPurWebFacadeTest extends BaseTestCase {

    private static final Logger logger = LoggerFactory.getLogger(SubsOrPurWebFacadeTest.class.getName());

    private static final Double FEE_RATE = 0.015;

    private static final Double DISCOUNT = 0.9;

    private static final Double ACTI_DISCOUNT = 0.5;

    private JSONObject jsonObject;

    @Autowired
    private SubsOrPurWebFacade subsOrPurWebFacade;


    @BeforeMethod
    public void setUp() throws IOException {

        logger.info("开始执行本单元测试.");

        String src = FileExtUtils.readFileToString("classpath:high/orders/trade/SubsOrPurWebRequest.json");

        jsonObject = JSON.parseObject(src);

        jsonObject.put("appointmentDealNo", RandomExtUtils.randomNumeric(9));
        jsonObject.put("dataTrack", RandomExtUtils.randomAlphabetic(32));
        jsonObject.put("externalDealNo", RandomExtUtils.randomAlphabetic(36));

        logger.info("预约订单号：{}", jsonObject.getString("appointmentDealNo"));

    }


    @AfterMethod
    public void tearDown() throws Exception {
        logger.info("单元测试结束.");
    }

    @Test(enabled = true, description = "高端网站下单场景")
    public void webFacadeTest() throws Exception {

        logger.info("json：{}", JSON.toJSON(jsonObject.toJSONString()).toString());

        SubsOrPurWebRequest request = JSON.parseObject(jsonObject.toJSONString(), SubsOrPurWebRequest.class);

        logger.info("请求内容：{}", JSON.toJSON(request).toString());

        SubsOrPurWebResponse response = subsOrPurWebFacade.execute(request);

        logger.info("返回结果：{}", JSON.toJSONString(response));

        if ("Z0000000".equals(response.getReturnCode())) {
            logger.info("测试通过,{}:{}", response.getReturnCode(), response.getDescription());
        } else {
            logger.error("测试失败,{}:{}", response.getReturnCode(), response.getDescription());
        }

        Assert.assertEquals(response.getReturnCode(), "Z0000000");

    }

    @Test(enabled = true, description = "高端产品极差校验失败")
    public void webFacadeTest01() throws Exception {

        // appAmt=2331050.00
        jsonObject.put("appAmt", 2500000);

        logger.info("json：{}", JSON.toJSON(jsonObject.toJSONString()).toString());

        SubsOrPurWebRequest request = JSON.parseObject(jsonObject.toJSONString(), SubsOrPurWebRequest.class);

        logger.info("请求内容：{}", JSON.toJSON(request).toString());

        SubsOrPurWebResponse response = subsOrPurWebFacade.execute(request);

        logger.info("返回结果：{}", JSON.toJSONString(response));

        if ("Z3000063".equals(response.getReturnCode())) {
            logger.info("测试通过,{}:{}", response.getReturnCode(), response.getDescription());
        } else {
            logger.error("测试失败,{}:{}", response.getReturnCode(), response.getDescription());
        }

        Assert.assertEquals(response.getReturnCode(), "Z3000063", "高端产品极差校验通过.");

    }

}
