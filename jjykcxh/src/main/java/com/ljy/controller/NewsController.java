package com.ljy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljy.VO.CommentVO;
import com.ljy.entity.Comment;
import com.ljy.entity.Msg;
import com.ljy.entity.News;
import com.ljy.entity.User;
import com.ljy.service.CommentService;
import com.ljy.service.NewsService;
import com.ljy.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    NewsService newsService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Msg getNews(){
        List<News> list=newsService.getNews();
        int count=newsService.getNewsCount();
        return Msg.success().add("news",list).add("count",count);
    }


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
        int nid=news.getNewsId();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Comment> comments = commentService.getCommentsByNewsId(nid);
        List<CommentVO> commentVOList=new ArrayList<>();
        for (Comment comment:comments){
            CommentVO commentVO=new CommentVO();
            BeanUtils.copyProperties(comment,commentVO);
            String updateTime = df.format(comment.getUpdateTime());
            commentVO.setUpdateTime(updateTime);
            User user = userService.getUserById(comment.getUserId());
            commentVO.setUserName(user.getUserName());
            commentVOList.add(commentVO);
        }

        //正常执行
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("detail");
        modelAndView.addObject("news",news);
        modelAndView.addObject("comments",commentVOList);
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

    @PostMapping("/deleteById")
    @ResponseBody
    public Msg deleteNewsById(@RequestParam("newsId") Integer id){
        int i = newsService.deleteById(id);
        if (i!=0){
            return Msg.success();
        }
        return Msg.fail();
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public Msg updateNews(@PathVariable("id") Integer id ,News news) throws ParseException {
        Date date=new Date();
        if (news.getNewsContext()==null||news.getNewsTitle()==null||news.getAuthorName()==null){
            return Msg.fail();
        }
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = df.format(date);
        Date time=df.parse(format);
        news.setNewsId(id);
        news.setUpdateTime(time);
        int i=newsService.updateById(news);
        System.out.println(news.getNewsContext());
        if (i!=0){
        return Msg.success();
        }
        return Msg.fail();
    }

    @PostMapping("/insertOne")
    @ResponseBody
    public Msg insertOne(News news) throws ParseException {
        if (news.getNewsContext()==null||news.getNewsTitle()==null||news.getAuthorName()==null){
            return Msg.fail();
        }
        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = df.format(date);
        Date time=df.parse(format);
        news.setUpdateTime(time);
        int i= newsService.insertOne(news);
        if (i!=0){
            return Msg.success();
        }
        return Msg.fail();
    }

}
