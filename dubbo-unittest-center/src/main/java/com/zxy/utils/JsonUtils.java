package com.zxy.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author yang.zhou
 * @date 2018/8/14
 */
public class JsonUtils {

    private final Logger logger = LoggerFactory.getLogger(JsonUtils.class.getName());

    public static void main(String[] args) {
        Gson gson = new Gson();
        int[] ints = {1,2,3,4,5};
        String[] strings = {"abc", "def", "ghi"};

        String s = gson.toJson(ints);



        System.out.println(s);

    }

}
