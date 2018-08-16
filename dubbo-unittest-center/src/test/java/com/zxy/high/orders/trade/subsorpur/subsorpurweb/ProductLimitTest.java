package com.zxy.high.orders.trade.subsorpur.subsorpurweb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurweb.SubsOrPurWebFacade;
import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurweb.SubsOrPurWebRequest;
import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurweb.SubsOrPurWebResponse;
import com.zxy.high.orders.BaseTestCase;
import com.zxy.utils.FileExtUtils;
import com.zxy.utils.MathCalcUtils;
import com.zxy.utils.RandomExtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author yang.zhou
 * @date 2018/8/16
 */
@Test(groups = {"functest"})
public class ProductLimitTest extends BaseTestCase {

    private static final Logger logger = LoggerFactory.getLogger(SubsOrPurWebFacadeTest.class.getName());

    private static final String FEE_RATE = "0.015";

    private static final String DISCOUNT = "0.9";

    private static final String ACTI_DISCOUNT = "0.5";

    private JSONObject jsonObject;

    @Autowired
    private SubsOrPurWebFacade subsOrPurWebFacade;


    @BeforeMethod
    public void setUp() throws IOException {

        logger.info(">>>>>>开始执行单元测试方法");

        String src = FileExtUtils.readFileToString("classpath:high/orders/trade/SubsOrPurWebRequest.json");

        jsonObject = JSON.parseObject(src);

        jsonObject.put("appointmentDealNo", RandomExtUtils.randomNumeric(9));
        jsonObject.put("dataTrack", RandomExtUtils.randomAlphabetic(32));
        jsonObject.put("externalDealNo", RandomExtUtils.randomAlphabetic(36));

    }


    @AfterMethod
    public void tearDown() throws Exception {
        logger.info(">>>>>>单元测试结束");
    }


    /**
     *
     * 产品购买金额低于产品最低追加申请金额
     * <p>产品CS0501 产品极差：100,追加净购买金额：1000，最低追加购买金额：10000</p>
     */
    @Test(enabled = true, description = "追加净购买金额小于产品最低追加申请净金额")
    public void add_purAmt_less_product_limit_test() throws Exception {

        String purchaseAmt = "900";

        BigDecimal esitmateFee = MathCalcUtils.multiply(purchaseAmt, FEE_RATE, DISCOUNT);

        BigDecimal appAmt = MathCalcUtils.add(purchaseAmt, esitmateFee);

        logger.info("申请金额{},手续费{}", appAmt, esitmateFee);

        jsonObject.put("appAmt", appAmt);
        jsonObject.put("esitmateFee", esitmateFee);

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
        Assert.assertEquals(response.getReturnCode(), "Z3000027", "交易金额小于追加认申购下限验证失败.");
    }


    /**
     *
     * 产品购买金额低于产品最低追加申请金额
     * <p>产品CS0501 产品极差：100,追加净购买金额：1000，最低追加购买金额：10000</p>
     */
    @Test(enabled = true, description = "追加净购买金额小于产品最低追加净申请金额")
    public void add_appAmt_less_product_limit_test() throws Exception {

        String fistPurchaseAmt = "8000";

        BigDecimal esitmateFee = MathCalcUtils.multiply(fistPurchaseAmt, FEE_RATE, DISCOUNT);

        BigDecimal appAmt = MathCalcUtils.add(fistPurchaseAmt, esitmateFee);

        logger.info("申请金额{},手续费{}", appAmt, esitmateFee);

        jsonObject.put("appAmt", appAmt);
        jsonObject.put("esitmateFee", esitmateFee);

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
        Assert.assertEquals(response.getReturnCode(), "Z3000027", "交易金额小于追加认申购下限验证失败.");
    }


}
