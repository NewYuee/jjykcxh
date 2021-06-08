package com.ljy.service;

import com.ljy.entity.Pic;

import java.util.List;

public interface PicService {
    List<Pic> getPicsByAid(Integer albumId);

    List<Pic> getAllPics();

    int deleteById(Integer id);

}
