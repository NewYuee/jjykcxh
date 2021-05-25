package com.ljy.service;


import com.ljy.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getComments();
    List<Comment> getCommentsByNewsId(Integer newsId);
    int insertComment(Comment comment);
    int deletCommentById(Integer id);
}
