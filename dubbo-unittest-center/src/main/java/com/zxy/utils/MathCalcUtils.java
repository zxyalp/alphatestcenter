package com.zxy.utils;


import java.math.BigDecimal;
import java.util.Optional;

/**
 * 数学计算工具类.
 * @author yang.zhou
 * @date 2018/8/15
 */
public class MathCalcUtils {

    /**
     * BigDecimal的加法运算方法
     */
//    public static BigDecimal add(){
//
//    }


    public static void main(String[] args) {
        BigDecimal bigDecimal01 = new BigDecimal("200");
        BigDecimal bigDecimal02 = new BigDecimal("200");


        BigDecimal bigDecimal= new BigDecimal("200");

        Optional<BigDecimal> optional = Optional.ofNullable(bigDecimal);

        System.out.println(optional.map(bigDecimal1 -> bigDecimal1.multiply(bigDecimal01)).map(bigDecimal1 -> bigDecimal1.multiply(bigDecimal02)).orElse(BigDecimal.ZERO));

    }


}
