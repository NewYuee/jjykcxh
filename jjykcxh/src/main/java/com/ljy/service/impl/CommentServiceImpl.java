package com.ljy.service.impl;

import com.ljy.VO.ReplyCommentVO;
import com.ljy.dao.CommentMapper;
import com.ljy.entity.Comment;
import com.ljy.entity.CommentExample;
import com.ljy.entity.User;
import com.ljy.service.CommentService;
import com.ljy.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    UserService userService;

    public List<Comment> getComments() {
        List<Comment> list = commentMapper.selectByExample(null);
        return list;
    }

    @Override
    public List<Comment> getCommentsByNewsId(Integer newsId) {
        return commentMapper.getCommentByNewsId(newsId);
    }

    @Override
    public int insertComment(Comment comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public void deletCommentById(Integer id) {
        commentMapper.deletCommentById(id);
    }

    @Override
    public List<Comment> getCommentsByAlbumId(Integer albumId) {
        return commentMapper.getCommentsByAlbumId(albumId);
    }

    @Override
    public List<ReplyCommentVO> getReplyCommentById(Integer commentId) {
        CommentExample example=new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andRepliedCommentIdEqualTo(commentId);
        List<Comment> list = commentMapper.selectByExample(example);
        List<ReplyCommentVO> replyCommentVOList=new ArrayList<>();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Comment item:list){
            ReplyCommentVO replyCommentVO = new ReplyCommentVO();
            BeanUtils.copyProperties(item,replyCommentVO);
            String updateTime = df.format(item.getUpdateTime());
            replyCommentVO.setUpdateTime(updateTime);
            User user =userService.getUserById(item.getUserId());
            replyCommentVO.setUserName(user.getUserName());
            replyCommentVOList.add(replyCommentVO);
        }
        return replyCommentVOList;
    }

    @Override
    public List<Comment> getCommentsInAlbum(Integer albumId) {
        CommentExample example=new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andAlbumIdEqualTo(albumId);
        return commentMapper.selectByExample(example);
    }

    @Override
    public Comment getComment1() {
        return commentMapper.getComment1();
    }

    @Override
    public int getCountByAid(Integer albumId) {
        CommentExample example=new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andAlbumIdEqualTo(albumId);
        return commentMapper.countByExample(example);
    }
}
