package com.bootdo.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;


public class TestCrc  {

    public static void main(String[] args){

        String input = "北京国税123456789</>9XXxx123456789XXxx</>北京市通州区正在大厦123456789</>中国银行123456789</>";

        try {

            byte[] inputs = input.getBytes("GBK");
            for (int i = 0; i < inputs.length; i ++) {
                div(inputs[i]);
            }
            byte r = 0;
            div(r);
            div(r);
            String crc3=Integer.toHexString(a);

            System.out.println(Integer.toHexString(a));

            String jm=getBase64(input+crc3.toUpperCase());//base64加密
            String s="$01"+jm+"$";

            String a="5YyX5Lqs5Zu956iOMTIzNDU2Nzg5PC8+OVhYeHgxMjM0NTY3ODlYWHh4PC8+5YyX5Lqs5biC6YCa5bee5Yy65q2j5Zyo5aSn5Y6mMTIzNDU2Nzg5PC8+5Lit5Zu96ZO26KGMMTIzNDU2Nzg5PC8+MjAxQQ==";
            System.out.println(s);
            String jmh1=getFromBase64(a);
            System.out.println(jmh1);


        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    static int a=0x0000;
    static int crc16=0x8005;

    private static void div(byte input) {//算法
        int temp=0;

        int data = input;
        for (int i = 0; i < 8; i ++) {
            temp = a & 0x8000;
            a = a << 1;
            a = a & 0x0000ffff;
            int numIn = data & 0x80;
            numIn = numIn >> 7;
            a = a ^ numIn;
            if (temp == 0x8000) {
                a = a ^ crc16;
            }
            data = data << 1;
            a = a & 0x0000ffff ;
        }
    }

    public static String getBase64(String str) {  //加密
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }


    public static String getFromBase64(String s) {  //解密
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
