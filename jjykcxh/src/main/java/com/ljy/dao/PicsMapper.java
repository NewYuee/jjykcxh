package com.ljy.dao;


import com.ljy.entity.Pic;
import com.ljy.entity.Pics;

import java.util.List;

public interface PicsMapper {
    List<Pics> getAll();
    Pics getOneById(Integer id);
    int insertOne(Pics pics);
    int updateById(Pics pics);
    int deleteById(Integer id);

    List<Pics> getByType(String type);
}
