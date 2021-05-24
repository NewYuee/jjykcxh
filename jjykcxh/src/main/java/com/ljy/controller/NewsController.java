package com.ljy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljy.entity.Msg;
import com.ljy.entity.News;
import com.ljy.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
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

/*    @RequestMapping("")
    public String toNewsListPge(Model model){
        List<News> list=newsService.getNews();
        model.addAttribute("newsList",list);
        return "news";
    }*/

    @RequestMapping("/detail")
    public ModelAndView toDetailPage(HttpServletRequest request){
        String newsId =request.getParameter("newsId");
        News news=null;
        //检验是否为符合要求的参数
        try {
            int id=Integer.parseInt(newsId);
            news = newsService.getNewsById(id);
        } catch (NumberFormatException e) {
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("news");
            return modelAndView;
        }
        //不存在的新闻
        if (news==null){
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("news");
            return modelAndView;
        }
        //正常执行
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("detail");
        modelAndView.addObject("news",news);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        modelAndView.addObject("updateTime",df.format(news.getUpdateTime()));
        return modelAndView;
    }

    @RequestMapping("/getNews")
    @ResponseBody
    public Msg getNewsByPnWithJson(@RequestParam(value = "pn",defaultValue = "1") Integer pn){
        //引入PageHelper进行分页操作，直接调用
        PageHelper.startPage(pn,10);
        //startPage后面紧跟分页查询
        List<News> list=newsService.getNews();
        PageInfo pageInfo=new PageInfo(list,4);
        return Msg.success().add("pageInfo",pageInfo);
    }

}
