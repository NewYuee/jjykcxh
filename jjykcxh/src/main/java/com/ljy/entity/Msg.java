package com.ljy.entity;

import java.util.HashMap;
import java.util.Map;

public class Msg {
    private int code;
    private String msg;
    private Map<String,Object> other=new HashMap<>();

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

    public static Msg success(){
        Msg result=new Msg();
        result.setCode(200);
        result.setMsg("处理成功！");
        return result;
    }

    public static Msg fail(){
        Msg result=new Msg();
        result.setCode(400);
        result.setMsg("处理失败！");
        return result;
    }

    public Map<String, Object> getOther() {
        return other;
    }

    public void setOther(Map<String, Object> other) {
        this.other = other;
    }

    public Msg add(String key, Object value){
        this.getOther().put(key, value);
        return this;
    }
}
