package com.ljy.test;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class EncodeTest {
    @Test
    public void EncodeTest() throws UnsupportedEncodingException {
        String text="测试文本";
        String text1=new String("测试文本");
        String encodeStr = EncodeUtil.EncodeStr(text);
        String encodeStr1 = EncodeUtil.EncodeStr(text1);
        System.out.println("encode===>"+encodeStr);
        System.out.println("encode1===>"+encodeStr1);
        String decodeStr = EncodeUtil.DecodeStr(encodeStr);
        System.out.println("decode===>"+decodeStr);
    }
}
