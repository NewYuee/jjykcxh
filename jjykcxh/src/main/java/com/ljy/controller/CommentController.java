package com.ljy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljy.VO.CommentVO;
import com.ljy.entity.Comment;
import com.ljy.entity.Msg;
import com.ljy.entity.User;
import com.ljy.service.CommentService;
import com.ljy.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Msg getComments(){
        List<Comment> list=commentService.getComments();
        return Msg.success().add("comments",list);
    }

    @RequestMapping("/getCommentsByNewsId")
    @ResponseBody
    public Msg getCommentByNewsId(@RequestParam(value = "newsId")Integer newsId){
        List<Comment> comments = commentService.getCommentsByNewsId(newsId);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<CommentVO> list=new ArrayList<>();
        for (Comment comment:comments){
            CommentVO commentVO=new CommentVO();
            BeanUtils.copyProperties(comment,commentVO);
            String updateTime = df.format(comment.getUpdateTime());
            commentVO.setUpdateTime(updateTime);
            User user = userService.getUserById(comment.getUserId());
            commentVO.setUserName(user.getUserName());
            list.add(commentVO);
        }
        return Msg.success().add("commentList",list);
    }

    @PostMapping("/save")
    @ResponseBody
    public Msg saveComment(@RequestParam String oSize,
                           @RequestParam String now,
                           @RequestParam String userName,
                           @RequestParam String newsId) throws ParseException {

        Integer userId=0;
        Integer nid=Integer.parseInt(newsId);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date updateTime=df.parse(now);
        //给userId赋值
        List<User> userList = userService.selectByUserName(userName);
        for (User user:userList){
            userId=user.getUserId();
        }
        Comment comment=new Comment(oSize,updateTime,null,null,nid,userId);
        if (commentService.insertComment(comment)==1){
            String s="发布成功";
            System.out.println(s);
            return Msg.success().add("info",s);
        }
        String s="评论失败，请稍后再试";
        System.out.println(s);
        return Msg.fail().add("info",s);
    }


    @PostMapping("/deleteCommentById")
    @ResponseBody
    public Msg deletCommentById(@RequestParam("commentId")Integer cid){
        int i = commentService.deletCommentById(cid);
        if (i==1){
            String info="删除成功！";
            return Msg.success().add("info",info);
        }
        String info="删除失败！";
        return Msg.fail().add("info",info);
    }

}
