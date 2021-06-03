package com.ljy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljy.VO.CommentVO;
import com.ljy.VO.ReplyCommentVO;
import com.ljy.entity.Comment;
import com.ljy.entity.Msg;
import com.ljy.entity.User;
import com.ljy.service.CommentService;
import com.ljy.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    @RequestMapping("/getCommentsByAlbumId")
    @ResponseBody
    public Msg getCommentByAlbumId(@RequestParam(value = "albumId")Integer albumId, Model model){
        List<Comment> comments = commentService.getCommentsByAlbumId(albumId);
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

        for (CommentVO commentVO:list){
            List<ReplyCommentVO> replyCommentById = commentService.getReplyCommentById(commentVO.getCommentId());
            commentVO.setRepliedComment(replyCommentById);
        }

        model.addAttribute("comments",list);
        return Msg.success().add("comments",list);
    }

    @PostMapping("/save")
    @ResponseBody
    public Msg saveAlbumComment(@RequestParam("userName")String userName,
                                @RequestParam("context") String context,
                                @RequestParam("replied_id") Integer repliedCid,
                                @RequestParam("time") String updateTime,
                                @RequestParam("albumId") Integer albumId,
                                @RequestParam("newsId") Integer newsId) throws ParseException {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time=df.parse(updateTime);
        int userId=0;
        List<User> userList = userService.selectByUserName(userName);
        if (userList.size()==0){
            String s="评论失败，请稍后再试";
            System.out.println(s);
            return Msg.fail().add("info",s);
        }
        for (User user:userList){
            userId=user.getUserId();
        }
        Comment comment=new Comment(context,time,albumId,repliedCid,newsId,userId);
        if (commentService.insertComment(comment)==1){
            String s="评论成功";
            Comment comment1=commentService.getComment1();
            return Msg.success().add("info",s).add("newCid",comment1.getCommentId());
        }
        String s="评论失败，请稍后再试";
        System.out.println(s);
        return Msg.fail().add("info",s);
    }

    @RequestMapping("/getCountByAid")
    @ResponseBody
    public Msg getCountByAid(@RequestParam("albumId") Integer albumId){
        int count=commentService.getCountByAid(albumId);
        return Msg.success().add("nums",count);
    }

    @PostMapping("/deleteCommentById")
    @ResponseBody
    public Msg deletCommentById(@RequestParam("commentId")Integer cid,@RequestParam("albumId") Integer albumId){
        commentService.deletCommentById(cid);
        if (albumId==0){
            String info="删除成功！";
            return Msg.success().add("info",info);
        }
        String info="删除成功！";
        int count=commentService.getCountByAid(albumId);
        return Msg.success().add("nums",count).add("info",info);
    }
}
