package com.ljy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljy.VO.CommentVO;
import com.ljy.entity.*;
import com.ljy.service.CommentService;
import com.ljy.service.NewsService;
import com.ljy.service.PicsService;
import com.ljy.service.UserService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    NewsService newsService;

    @Autowired
    PicsService picsService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Msg getNews(HttpServletRequest request){
        if(request.getParameter("keyword")!=null){
            String k="%"+request.getParameter("keyword")+"%";
            List<News> newsList=newsService.getNewsByKeyWord(k);
            return Msg.success().add("news",newsList).add("count",newsList.size());
        }
        List<News> list=newsService.getNews();
        int count=newsService.getNewsCount();
        return Msg.success().add("news",list).add("count",count);
    }


    @PostMapping("/uploadpic")
    @ResponseBody
    public PicsJson uploadInAlbum(MultipartFile file) throws UnsupportedEncodingException {
        //1.定义上传文件服务器的路径
        String path="http://120.78.196.234/resource/jjykcxh/news/";
        //获取上传文件的名称
        String originalFilename = file.getOriginalFilename();
        String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().substring(0,10).replace("-", "");
        String filename=uuid+suffix;
        //2.1.创建客户端的对象
        Client client = Client.create();
        String encodeFileName = URLEncoder.encode(filename,"UTF-8");
        System.out.println(encodeFileName);
        //3.和图片服务器进行连接,拿到一个web资源
        WebResource webResource = client.resource(path + encodeFileName);
        if(webResource==null){
            return null;
        }
        //4.完成文件上传(getBytes以字节方式传)
        try {
            webResource.put(file.getBytes());
        } catch (IOException e) {
            return null;
        }
        System.out.println("文件上传成功<========="+encodeFileName);

        PicsJson picsJson=new PicsJson();
        Pics pics=new Pics();
        pics.setPname(encodeFileName);
        pics.setSrc(path + encodeFileName);
        pics.setType("1");
        int i=picsService.insertOne(pics);
        if (i==0){
            return null;
        }

        picsJson.setCode(0);
        picsJson.setMsg("ok");
        Map<String,Object> data= new HashMap<>();
        data.put("src",pics.getSrc());
        data.put("title",pics.getPname());
        picsJson.setData(data);
        return picsJson;
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
