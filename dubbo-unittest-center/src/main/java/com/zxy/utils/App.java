package com.zxy.utils;


import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;

/**
 * @author yang.zhou
 * @date 2018/8/14
 */
public class App {
    public static void main(String[] args) {

//        for(int i = 0; i<2000; i += 1){
//            int num = ThreadLocalRandom.current().nextInt(10, 101);
//            System.out.println(num);
//        }

        // 使用指定的字符生成5位长度的随机字符串
        String s1 = RandomStringUtils.random(5, new char[]{'a', 'b', 'c', 'd', '1', '2', '3','4', '-'});

        // 生成随机[a-z]字符串, 包含大小写
        String s = RandomStringUtils.randomAlphabetic(16);

        // 生成指定长度的字母和数字随机组合字符串
        String s2 = RandomStringUtils.randomAlphanumeric(16);

        // 生成随机数字符串
        String s3 = RandomStringUtils.randomNumeric(6, 10);

        String s4 = RandomStringUtils.randomGraph(10);

//        System.out.println(s2);
        System.out.println(s3);
//        System.out.println(s4);


        int r = RandomUtils.nextInt(200,202);
        System.out.println(r);

        System.out.println(getRandomInt(200,300));

    }

    public static String getRandomInt(int min, int max){
        return Integer.toString(min+(int)(Math.random() * (max-min)+1));
    }



}
