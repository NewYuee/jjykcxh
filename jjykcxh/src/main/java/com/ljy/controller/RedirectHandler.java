package com.ljy.controller;

import com.ljy.com.ljy.util.CookieUtil;
import com.ljy.entity.Msg;
import com.ljy.entity.User;
import com.ljy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class RedirectHandler {

    @Autowired
    UserService userService;

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url){
        return url;
    }

    @GetMapping("/")
    public String main(HttpServletRequest request,HttpSession session){
        Cookie loginInfo = CookieUtil.getCookieByName(request, "loginInfo");
        String[] res=new String[3];
        if (loginInfo!=null){
            res=loginInfo.getValue().split("&");
            User user = new User();
            user.setUserName(res[0]);
            user.setUserPwd(res[1]);
            User user1 = userService.selectOne(user);
            String id=res[2];
            if (id.equals("1")){
                session.setAttribute("admin",user1);
                session.setAttribute("userInfo",user1);
            }else if (id.equals("0")){
                session.setAttribute("userInfo",user1);
            }
        }
        return "index";
    }

    @GetMapping("/admin/{class}/{item}")
    public String doMain(@PathVariable("class") String sc, @PathVariable("item") String item, HttpServletRequest request, HttpServletResponse response){

        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("admin");
        if (user==null){
            return "login";
        }
        else {
            String surl="Admin/"+sc+"/"+item;
            return surl;
        }
//        String surl="Admin/"+sc+"/"+item;
//        return surl;

    }
}
