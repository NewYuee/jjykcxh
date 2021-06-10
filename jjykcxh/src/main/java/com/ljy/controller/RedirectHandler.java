package com.ljy.controller;

import com.ljy.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class RedirectHandler {

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url){
        return url;
    }

    @GetMapping("/")
    public String main(){
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
