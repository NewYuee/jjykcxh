package com.ljy.entity;

import com.ljy.VO.PicVO;

import java.util.ArrayList;
import java.util.List;

public class AlbumJson {
    private String title;
    private Integer id;
    private Integer start;
    private List<PicVO> data=new ArrayList<>();

    public AlbumJson(String title, Integer id, Integer start, List<PicVO> data) {
        this.title = title;
        this.id = id;
        this.start = start;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public List<PicVO> getData() {
        return data;
    }

    public void setData(List<PicVO> data) {
        this.data = data;
    }
}
