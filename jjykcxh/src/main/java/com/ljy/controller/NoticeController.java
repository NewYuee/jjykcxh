package com.ljy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljy.entity.Msg;
import com.ljy.entity.Notice;
import com.ljy.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;


/*    @RequestMapping("")
    public String toNotice(){
        return "notice";
    }*/

    @RequestMapping("/getAll")
    @ResponseBody
    public Msg getAll(){
        List<Notice> list=noticeService.getAll();
        return Msg.success().add("notices",list);
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
}
