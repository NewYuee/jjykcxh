package com.ljy.controller;

import com.ljy.entity.Msg;
import com.ljy.entity.News;
import com.ljy.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    NewsService newsService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Msg getNews(){
        List<News> list=newsService.getNews();
        return Msg.success().add("news",list);
    }

    @RequestMapping("")
    public String toNewsListPge(Model model){
        List<News> list=newsService.getNews();
        model.addAttribute("newsList",list);
        return "news";
    }


}
