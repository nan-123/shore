package com.yougou.util;

import java.util.Random;

public class StringUtil {

    final static String RANDOM_STR="abcdefghijklmnpqrstuvwxyz0123456789";

    public static String getRandomStr(int length){
        StringBuffer sb=new StringBuffer();
        if (length > 0){
            Random random=new Random();
            for (int i = 0; i < length; i++){
                int number=random.nextInt(RANDOM_STR.length());
                sb.append(RANDOM_STR.charAt(number));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getRandomStr(6));
    }
}
