package com.ljy.VO;

public class ReplyCommentVO {
    private Integer commentId;

    private String userName;

    private String commentContext;

    private String updateTime;

    private Integer albumId;

    private Integer repliedCommentId;

    private Integer newsId;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentContext() {
        return commentContext;
    }

    public void setCommentContext(String commentContext) {
        this.commentContext = commentContext;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getRepliedCommentId() {
        return repliedCommentId;
    }

    public void setRepliedCommentId(Integer repliedCommentId) {
        this.repliedCommentId = repliedCommentId;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }
}
