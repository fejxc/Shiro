package com.sy.utils;

import java.util.Random;

public class SAltUtils {
    public static String getSalt(int n){
//        commd + shift + u 转小写
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String salt = getSalt(32);
        System.out.println(salt);

    }
}
