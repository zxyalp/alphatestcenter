package com.zxy.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;
import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurweb.SubsOrPurWebRequest;

import java.io.File;
import java.io.IOException;

/**
 * @author yang.zhou
 * @date 2018/8/15
 */
public class FileExtUtils {

    public static void main(String[] args) throws IOException {

        File f =ResourceUtils.getFile("classpath:high/orders/trade/SubsOrPurWebRequest.json");

        String s = FileUtils.readFileToString(f, "utf-8");

        System.out.println(s);

        JSONObject jsonObject = JSON.parseObject(s);



        System.out.println(jsonObject.getBigDecimal("appAmt"));
        System.out.println(jsonObject.getString("appointmentDealNo"));
        System.out.println();

        System.out.println(JSON.toJSON(s).toString());



        Gson gson = new GsonBuilder().setPrettyPrinting().create();

//        JsonParser parser = new JsonParser();
//        JsonElement element = parser.parse(s);
        String pretty = gson.toJson(s);

        System.out.println(pretty);

    }

    public static String readFileToString(String resourceLocation) throws IOException {
        File file = ResourceUtils.getFile(resourceLocation);
        return FileUtils.readFileToString(file, "utf-8");
    }


}
