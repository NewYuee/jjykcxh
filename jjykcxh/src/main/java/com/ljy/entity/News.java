package com.ljy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class News {
    private Integer newsId;

    private String newsTitle;

    private String authorName;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="UTC")
    private Date updateTime;

    private String newsContext;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle == null ? null : newsTitle.trim();
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName == null ? null : authorName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getNewsContext() {
        return newsContext;
    }

    public void setNewsContext(String newsContext) {
        this.newsContext = newsContext == null ? null : newsContext.trim();
    }
}