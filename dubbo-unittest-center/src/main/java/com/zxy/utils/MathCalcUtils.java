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
    public static BigDecimal add(BigDecimal b1, BigDecimal... bn){

        BigDecimal s = Optional.ofNullable(b1).orElse(BigDecimal.ZERO);

        if (null != bn) {
            for(BigDecimal b : bn){
                s = s.add(Optional.ofNullable(b).orElse(BigDecimal.ZERO));
            }
        }
        return s;
    }


    public static Integer add(Integer b1, Integer... bn){

        Integer s = Optional.ofNullable(b1).orElse(0);

        if (null != bn){
            for (Integer b: bn){
                s += Optional.ofNullable(b).orElse(0);
            }
        }
        return s > 0 ? s : 0;
    }

    public static <T extends Number> BigDecimal add(T b1, T... bn){
        BigDecimal s = Optional.of(BigDecimal.valueOf(b1.doubleValue())).orElse(BigDecimal.ZERO);

        if(null != bn){
            for (T b: bn){
                s=s.add(BigDecimal.valueOf(b.doubleValue()));
            }
        }
        return s;
    }

    /**
     * BigDecimal的加法运算方法
     */
    public static BigDecimal add(String b1, BigDecimal... bn){

        BigDecimal s = Optional.of(new BigDecimal(b1)).orElse(BigDecimal.ZERO);

        if (null != bn) {
            for(BigDecimal b : bn){
                s = s.add(Optional.ofNullable(b).orElse(BigDecimal.ZERO));
            }
        }
        return s;
    }

    /**
     *  BigDecimal减法运算,减法结果为负数时，默认返回0
     * @param b1
     * @param bn
     * @return
     */

    public static BigDecimal sub(BigDecimal b1, BigDecimal bn){
        return sub(true, b1, bn);
    }

    /**
     * 减法运算
     * @param isZero
     * @param b1
     * @param bn
     * @return
     */
    public static BigDecimal sub(Boolean isZero, BigDecimal b1, BigDecimal... bn){
        BigDecimal s = Optional.ofNullable(b1).orElse(BigDecimal.ZERO);

        if(null != bn){
            for (BigDecimal b: bn){
                s = s.subtract(Optional.ofNullable(b).orElse(BigDecimal.ZERO));
            }
        }
        return isZero?(s.compareTo(BigDecimal.ZERO) < 0 ?BigDecimal.ZERO:s):s;
    }


    /**
     * 除法运算，返回2位小数，除数或是被除数为0，返回0.
     * @param b1
     * @param b2
     * @param <T>
     * @return
     */
    public static <T extends Number> BigDecimal div(T b1, T b2){
        return div(b1, b2, BigDecimal.ZERO);
    }


    /**
     * BigDecimal的除法封装， 如果除数或是被除数为0，返回默认值
     * @param b1
     * @param b2
     * @param defaultValue
     * @param <T>
     * @return
     */
    public static <T extends Number> BigDecimal div(T b1, T b2, BigDecimal defaultValue){
        if( null == b1 || null == b2){
            return defaultValue;
        }

        try{
            return BigDecimal.valueOf(b1.doubleValue()).divide(BigDecimal.valueOf(b2.doubleValue()), 2, BigDecimal.ROUND_DOWN);
        }catch (Exception e){
            return defaultValue;
        }
    }


    /**
     * 乘法计算
     * @param b1
     * @param bn
     * @param <T>
     * @return
     */
    public static <T extends Number> BigDecimal multiply(T b1, T... bn){
        if (null == b1|| null == bn){
            return BigDecimal.ZERO;
        }

        BigDecimal s = BigDecimal.valueOf(b1.doubleValue());

        for (T b: bn){
            s = s.multiply(BigDecimal.valueOf(b.doubleValue()));
        }

        return s.setScale(2, BigDecimal.ROUND_DOWN);
    }


    /**
     * 乘法计算
     * @param b1
     * @param bn
     * @return
     */
    public static BigDecimal multiply(String b1, String... bn){
        if(null == b1 || null == bn){
            return BigDecimal.ZERO;
        }

        BigDecimal s = new BigDecimal(b1);

        for(String b: bn){
            s = s.multiply(new BigDecimal(b));
        }
        return s.setScale(2, BigDecimal.ROUND_DOWN);

    }

    public static void main(String[] args) {
        BigDecimal bigDecimal01 = new BigDecimal("200");
        BigDecimal bigDecimal02 = new BigDecimal("200");
        BigDecimal bigDecimal03 = new BigDecimal("200");

        Double a1 = 100.22;
        Double b1 = 200.22;
        Double b2 = 0.00;


        System.out.println(multiply(a1, b1, b2));

        System.out.println(add(bigDecimal01, bigDecimal02));

        int a = 2000;
        int b = 3000;

        System.out.println(add(a,b));

        System.out.println(bigDecimal01.multiply(bigDecimal02));

    }


}
