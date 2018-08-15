package com.zxy.utils;

import com.google.gson.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;


/**
 * @author yang.zhou
 * @date 2018/8/14
 */
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class.getName());

    private static final Gson gson = new GsonBuilder().serializeNulls().create();


    public static String toJson(Object src){
          return  gson.toJson(src);
    }

    public static String toJson(Object src, Type typeOfSrc){
        return  gson.toJson(src, typeOfSrc);
    }

    public static String tpFormatJson(String src){
        Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(src);
        return gson1.toJson(element);
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        int[] ints = {1,2,3,4,5};
        String[] strings = {"abc", "def", "ghi"};

        String s = gson.toJson(ints);



        System.out.println(s);

    }

}
