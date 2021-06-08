package com.ljy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/admin/index")
    public String red(){
        return "Admin/index";
    }

    @GetMapping("/admin/{class}/{item}")
    public String doMain(@PathVariable("class") String sc,@PathVariable("item") String item){
        String surl="Admin/"+sc+"/"+item;
        return surl;
    }
}
