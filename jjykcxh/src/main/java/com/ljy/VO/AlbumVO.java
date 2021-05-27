package com.ljy.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ljy.entity.Comment;
import com.ljy.entity.Pic;

import java.util.*;

public class AlbumVO {
    private Integer albumId;

    private String albumName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="UTC")
    private Date updateTime;

    private String authorName;

    private Integer c_count;

    public Integer getC_count() {
        return c_count;
    }

    public void setC_count(Integer c_count) {
        this.c_count = c_count;
    }

    private List<Pic> pics=new ArrayList<>();

//    private List<Comment> comments=new ArrayList<>();

    public List<Pic> getPics() {
        return pics;
    }

    public void setPics(List<Pic> pics) {
        this.pics = pics;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

}
