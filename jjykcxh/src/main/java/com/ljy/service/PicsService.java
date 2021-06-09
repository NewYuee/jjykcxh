package com.ljy.service;

import com.ljy.entity.Pics;

import java.util.List;

public interface PicsService {
    List<Pics> getAll();
    Pics getOneById(Integer id);
    int insertOne(Pics pics);
    int updateById(Pics pics);
    int deleteById(Integer id);

    List<Pics> getByType(String type);
}
