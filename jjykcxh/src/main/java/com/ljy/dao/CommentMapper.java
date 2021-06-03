package com.ljy.dao;

import com.ljy.entity.Comment;
import com.ljy.entity.CommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;


public interface CommentMapper {
    int countByExample(CommentExample example);

    int deleteByExample(CommentExample example);


    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    List<Comment> getCommentByNewsId(Integer id);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    //得到评论，但不包括包含回复的
    List<Comment> getCommentsByAlbumId(Integer albumId);

    //得到指定id的回复的评论
    List<Comment> getReplyCommentById(Integer commentId);

    Comment getComment1();

    void deletCommentById(Integer id);
}