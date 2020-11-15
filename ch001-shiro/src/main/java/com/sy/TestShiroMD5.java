package com.sy;

import org.apache.shiro.crypto.hash.Md5Hash;

public class TestShiroMD5 {
    public static void main(String[] args) {
        //创建MD5算法
//        Md5Hash md5Hash = new Md5Hash();
//        md5Hash.setBytes("123".getBytes());
//        String s = md5Hash.toHex();
//        System.out.println(s);

        //使用md5
        Md5Hash md5Hash = new Md5Hash("12345");
        System.out.println(md5Hash.toHex());

        //使用md5 加salt处理
        Md5Hash md5Hash1 = new Md5Hash("12345","hfjrh");
        System.out.println(md5Hash1.toHex());

        //使用md5 加salt处理 哈希散列
        Md5Hash md5Hash2 = new Md5Hash("12345","hfjrh",1024);
        System.out.println(md5Hash2.toHex());
    }
}
