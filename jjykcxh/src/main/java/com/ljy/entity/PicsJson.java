package com.ljy.entity;

import java.util.HashMap;
import java.util.Map;

public class PicsJson {
    private int code;
    private String msg;
    private Map<String,Object> data=new HashMap();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
