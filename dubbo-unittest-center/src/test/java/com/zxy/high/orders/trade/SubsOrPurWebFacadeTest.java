package com.zxy.high.orders.trade;


import com.zxy.high.orders.BaseTestCase;
import com.zxy.utils.RandomExtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurweb.SubsOrPurWebFacade;
import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurweb.SubsOrPurWebRequest;
import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurweb.SubsOrPurWebResponse;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import com.alibaba.fastjson.JSON;


/**
 * @author yang.zhou
 * @date 2018/8/14
 */
public class SubsOrPurWebFacadeTest extends BaseTestCase {

    private final Logger logger = LoggerFactory.getLogger(SubsOrPurWebFacadeTest.class.getName());

    @Autowired
    private SubsOrPurWebFacade subsOrPurWebFacade;


    public LinkedHashMap<String, String> getDefaultParameters() {

        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("appAmt", "2331050.0");
        params.put("appDt", "20180716");
        params.put("appTm", "035641");
        params.put("appointmentDealNo", RandomExtUtils.randomNumeric(9));
        params.put("consCode", "");
        params.put("cpAcctNo", "2018071100761469");
        params.put("dataTrack", RandomExtUtils.randomAlphabetic(32));
        params.put("disCode", "HB000A001");
        params.put("esitmateFee", "31050.0");
        params.put("externalDealNo", RandomExtUtils.randomAlphabetic(36));
        params.put("fundCode", "CS0501");
        params.put("fundShareClass", "A");
        params.put("hbOneNo", "");
        params.put("operIp", "192.168.121.20");
        params.put("operatorNo", "");
        params.put("outletCode", "H20140901");
        params.put("pageNo", "1");
        params.put("pageSize", "20");
        params.put("paymentType", "04");
        params.put("protocolNo", "");
        params.put("protocolType", "4");
        params.put("riskFlag", "1");
        params.put("subBankName", "");
        params.put("transactorIdNo", "");
        params.put("transactorIdType", "");
        params.put("transactorName", "");
        params.put("txAcctNo", "1100876013");
        params.put("txChannel", "2");
        params.put("txCode", "Z330007");
        params.put("txPwd", "Bh/z377qeWnFKsfud212ww0XaXPFkp3RKU4ttpGpF3VwaevH8aUZCdwzk7ohO5ovDdC7s7iKE1k=");
        return params;
    }


    @Test(description = "高端网站下单场景")
    public void webFacadeTest() throws Exception{

        LinkedHashMap<String, String> context = this.getDefaultParameters();

        SubsOrPurWebRequest request = new SubsOrPurWebRequest();

        request.setAppAmt(new BigDecimal(context.get("appAmt")));
        request.setAppDt(context.get("appDt"));
        request.setAppTm(context.get("appTm"));
        request.setAppointmentDealNo(context.get("appointmentDealNo"));
        request.setConsCode(context.get("consCode"));
        request.setCpAcctNo(context.get("cpAcctNo"));
        request.setDataTrack(context.get("dataTrack"));
        request.setDisCode(context.get("disCode"));
        request.setEsitmateFee(new BigDecimal(context.get("esitmateFee")));
        request.setExternalDealNo(context.get("externalDealNo"));
        request.setFundCode(context.get("fundCode"));
        request.setFundShareClass(context.get("fundShareClass"));
        request.setHbOneNo(context.get("hbOneNo"));
        request.setOperIp(context.get("operIp"));
        request.setOperatorNo(context.get("operatorNo"));
        request.setOutletCode(context.get("outletCode"));
        request.setPageNo(Integer.parseInt(context.get("pageNo")));
        request.setPageSize(Integer.parseInt(context.get("pageSize")));
        request.setPaymentType(context.get("paymentType"));
        request.setProtocolNo(context.get("protocolNo"));
        request.setProtocolType(context.get("protocolType"));
        request.setRiskFlag(context.get("riskFlag"));
        request.setSubBankName(context.get("subBankName"));
        request.setTransactorIdNo(context.get("transactorIdNo"));
        request.setTransactorIdType(context.get("transactorIdType"));
        request.setTransactorName(context.get("transactorName"));
        request.setTxAcctNo(context.get("txAcctNo"));
        request.setTxChannel(context.get("txChannel"));
        request.setTxCode(context.get("txCode"));
        request.setTxPwd(context.get("txPwd"));

        logger.info("请求内容：{}", JSON.toJSONString(request));

        SubsOrPurWebResponse response = subsOrPurWebFacade.execute(request);

        logger.info("返回结果：{}", JSON.toJSONString(response));

        if ("Z0000000".equals(response.getReturnCode())){
            logger.info("测试通过,{}:{}",response.getReturnCode(), response.getDescription());
        }else {
            logger.error("测试失败,{}:{}",response.getReturnCode(), response.getDescription());
        }

        Assert.assertEquals(response.getReturnCode(), "Z0000000");

    }

    @Test(description = "高端产品极差校验失败")
    public void webFacadeTest01() throws Exception{

        LinkedHashMap<String, String> context = this.getDefaultParameters();

        SubsOrPurWebRequest request = new SubsOrPurWebRequest();

        request.setAppAmt(new BigDecimal("2000000"));
        request.setAppDt(context.get("appDt"));
        request.setAppTm(context.get("appTm"));
        request.setAppointmentDealNo(context.get("appointmentDealNo"));
        request.setConsCode(context.get("consCode"));
        request.setCpAcctNo(context.get("cpAcctNo"));
        request.setDataTrack(context.get("dataTrack"));
        request.setDisCode(context.get("disCode"));
        request.setEsitmateFee(new BigDecimal(context.get("esitmateFee")));
        request.setExternalDealNo(context.get("externalDealNo"));
        request.setFundCode(context.get("fundCode"));
        request.setFundShareClass(context.get("fundShareClass"));
        request.setHbOneNo(context.get("hbOneNo"));
        request.setOperIp(context.get("operIp"));
        request.setOperatorNo(context.get("operatorNo"));
        request.setOutletCode(context.get("outletCode"));
        request.setPageNo(Integer.parseInt(context.get("pageNo")));
        request.setPageSize(Integer.parseInt(context.get("pageSize")));
        request.setPaymentType(context.get("paymentType"));
        request.setProtocolNo(context.get("protocolNo"));
        request.setProtocolType(context.get("protocolType"));
        request.setRiskFlag(context.get("riskFlag"));
        request.setSubBankName(context.get("subBankName"));
        request.setTransactorIdNo(context.get("transactorIdNo"));
        request.setTransactorIdType(context.get("transactorIdType"));
        request.setTransactorName(context.get("transactorName"));
        request.setTxAcctNo(context.get("txAcctNo"));
        request.setTxChannel(context.get("txChannel"));
        request.setTxCode(context.get("txCode"));
        request.setTxPwd(context.get("txPwd"));

        logger.info("请求内容：{}", JSON.toJSONString(request));

        SubsOrPurWebResponse response = subsOrPurWebFacade.execute(request);

        logger.info("返回结果：{}", JSON.toJSONString(response));

        if ("Z3000063".equals(response.getReturnCode())){
            logger.info("测试通过,{}:{}",response.getReturnCode(), response.getDescription());
        }else {
            logger.error("测试失败,{}:{}",response.getReturnCode(), response.getDescription());
        }

        Assert.assertEquals(response.getReturnCode(), "Z3000063", "高端产品极差校验通过.");

    }

}
