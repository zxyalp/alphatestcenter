package com.zxy.utils;


import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;


/**
 * @author yang.zhou
 * @date 2018/8/14
 */
public class RandomExtUtils extends RandomStringUtils{

    public static String range(int start, int end){
        return Integer.toString(start+(int)(Math.random() * (start-end)+1));
    }
}
