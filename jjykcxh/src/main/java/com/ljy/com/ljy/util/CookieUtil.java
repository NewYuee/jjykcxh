package com.ljy.com.ljy.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {
    public static Map<String, Cookie> getCookiesMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap=new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie:cookies){
                cookieMap.put(cookie.getName(),cookie);
            }
        }
        return cookieMap;
    }

    public static Cookie getCookieByName(HttpServletRequest request,String name){
        Map<String, Cookie> cookiesMap = getCookiesMap(request);
        if (cookiesMap.containsKey(name)){
            return cookiesMap.get(name);
        }
        return null;
    }
}
