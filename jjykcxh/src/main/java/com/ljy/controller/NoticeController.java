package com.ljy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljy.entity.Msg;
import com.ljy.entity.News;
import com.ljy.entity.Notice;
import com.ljy.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Msg getAll(HttpServletRequest request){
        if(request.getParameter("keyword")!=null){
            String k="%"+request.getParameter("keyword")+"%";
            List<Notice> noticeList=noticeService.getNewsByKeyWord(k);
            return Msg.success().add("notices",noticeList).add("count",noticeList.size());
        }
        List<Notice> list=noticeService.getAll();
        return Msg.success().add("notices",list).add("count",list.size());
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public Msg updateNotice(@PathVariable("id") Integer id,Notice notice) throws ParseException {
        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        notice.setUpdateTime(df.parse(df.format(date)));
        notice.setNoticeId(id);
        int i = noticeService.updateById(notice);
        if(i!=0){
            return Msg.success();
        }
        return Msg.fail();
    }

    //查询通知并进行pageHelper分页
    @RequestMapping("getNotices")
    @ResponseBody
    public Msg getNotices(@RequestParam(value = "pn",defaultValue = "1") Integer pn){
        PageHelper.startPage(pn,10);
        List<Notice> notices = noticeService.getAll();
        PageInfo pageInfo=new PageInfo(notices,4);
        return Msg.success().add("pageInfo",pageInfo);
    }


    @PostMapping("/deleteById")
    @ResponseBody
    public Msg deleteById(@RequestParam("id") Integer id){
        int i=noticeService.deleteById(id);
        if(i!=0){
            return Msg.success();
        }
        return Msg.fail();
    }


    @RequestMapping("/detail")
    //get通知详情，返还界面
    public String getDetail(HttpServletRequest request, Model model){
        String sid=request.getParameter("noticeId");
        Notice notice=null;
        try {
            int id=Integer.parseInt(sid);
            notice=noticeService.getNoticeById(id);
        } catch (NumberFormatException e) {
            return "notice";
        }
        if (notice==null){
            return "notice";
        }
        model.addAttribute("notice",notice);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("updateTime",df.format(notice.getUpdateTime()));
        return "detail_notice";
    }

    @PostMapping("/insertOne")
    @ResponseBody
    public Msg insertOne(Notice notice) throws ParseException {
        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        notice.setUpdateTime(df.parse(df.format(date)));
        int i=noticeService.insertOne(notice);
        if(i!=0){
            return Msg.success();
        }
        return Msg.fail();
    }

}
