package com.ljy.controller;

import com.ljy.entity.Msg;
import com.ljy.entity.Notice;
import com.ljy.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Msg getAll(){
        List<Notice> list=noticeService.getAll();
        return Msg.success().add("notices",list);
    }

}
