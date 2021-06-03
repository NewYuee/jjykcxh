package com.ljy.VO;

import com.ljy.entity.Pic;

import java.util.ArrayList;
import java.util.List;

public class AlbumVO {
    private Integer albumId;

    private String albumName;

    private String updateTime;

    private String authorName;

    private Integer c_count;

    public Integer getC_count() {
        return c_count;
    }

    public void setC_count(Integer c_count) {
        this.c_count = c_count;
    }

    private List<Pic> pics=new ArrayList<>();


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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

}
