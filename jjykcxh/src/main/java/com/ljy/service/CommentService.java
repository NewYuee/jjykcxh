package com.ljy.service;


import com.ljy.VO.ReplyCommentVO;
import com.ljy.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getComments();
    List<Comment> getCommentsByNewsId(Integer newsId);
    int insertComment(Comment comment);
    void deletCommentById(Integer id);

    List<Comment> getCommentsByAlbumId(Integer albumId);

    List<ReplyCommentVO> getReplyCommentById(Integer commentId);

    List<Comment> getCommentsInAlbum(Integer albumId);

    Comment getComment1();

    int getCountByAid(Integer albumId);
}
