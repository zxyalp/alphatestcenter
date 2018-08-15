package com.zxy.high.orders.trade;


import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurweb.SubsOrPurWebRequest;

import com.zxy.utils.RandomExtUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * @author yang.zhou
 * @date 2018/8/15
 */
public class AppTest {

    private static final Logger logger = LoggerFactory.getLogger(AppTest.class.getName());

    private static final Double FEE_RATE = 0.015;

    private static final Double DISCOUNT = 0.9;

    private static final Double ACTI_DISCOUNT = 0.5;

    @Test
    public void test() throws IOException {

        File file = ResourceUtils.getFile("classpath:high/orders/trade/SubsOrPurWebRequest.json");

        String src = FileUtils.readFileToString(file, "utf-8");

        Type type = new TypeReference<HashMap<String, Object>>() {}.getType();

         JSONObject jsonObject = JSON.parseObject(src);

        jsonObject.put("appointmentDealNo", RandomExtUtils.randomNumeric(9));
        jsonObject.put("dataTrack", RandomExtUtils.randomAlphabetic(32));
        jsonObject.put("externalDealNo", RandomExtUtils.randomAlphabetic(36));

        System.out.println(jsonObject.toString());

    }


    @Test
    public void test02(){
        BigDecimal decimal = new BigDecimal("20000073.28728");
        BigDecimal decimal01 = BigDecimal.valueOf(FEE_RATE);
        BigDecimal decimal02 = BigDecimal.valueOf(DISCOUNT);

        String decimal03 = decimal.multiply(decimal01).multiply(decimal02).setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();


        System.out.println(decimal01);
        System.out.println(decimal02);
        System.out.println(decimal03);


    }



}
