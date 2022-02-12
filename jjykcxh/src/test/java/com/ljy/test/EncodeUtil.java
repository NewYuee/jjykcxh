package com.ljy.test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncodeUtil {
    public static String EncodeStr(String str) throws UnsupportedEncodingException {
        Base64.Encoder encoder=Base64.getEncoder();
        return encoder.encodeToString(str.getBytes("UTF-8"));
    }
    public static String DecodeStr(String str) throws UnsupportedEncodingException {
        Base64.Decoder decoder= Base64.getDecoder();
        return new String(decoder.decode(str),"UTF-8");
    }
}
