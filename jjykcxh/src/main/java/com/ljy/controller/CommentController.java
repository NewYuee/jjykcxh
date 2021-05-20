package com.ljy.controller;

import com.ljy.entity.Comment;
import com.ljy.entity.Msg;
import com.ljy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Msg getComments(){
        List<Comment> list=commentService.getComments();
        return Msg.success().add("comments",list);
    }


}
