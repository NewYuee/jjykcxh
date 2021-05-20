package com.ljy.service.impl;

import com.ljy.dao.CommentMapper;
import com.ljy.entity.Comment;
import com.ljy.entity.CommentExample;
import com.ljy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentMapper commentMapper;

    public List<Comment> getComments() {
        List<Comment> list = commentMapper.selectByExample(null);
        return list;
    }

    @Override
    public List<Comment> getCommentsByNewsId(Integer newsId) {
        CommentExample example=new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andNewsIdEqualTo(newsId);
        List<Comment> list=commentMapper.selectByExample(example);
        return list;
    }
}