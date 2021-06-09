package com.ljy.service.impl;

import com.ljy.dao.PicsMapper;
import com.ljy.entity.Pic;
import com.ljy.entity.Pics;
import com.ljy.service.PicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PicsServiceImpl implements PicsService {
    @Autowired
    PicsMapper picsMapper;

    @Override
    public List<Pics> getAll() {
        return picsMapper.getAll();
    }

    @Override
    public Pics getOneById(Integer id) {
        return picsMapper.getOneById(id);
    }

    @Override
    public int insertOne(Pics pics) {
        return picsMapper.insertOne(pics);
    }

    @Override
    public int updateById(Pics pics) {
        return picsMapper.updateById(pics);
    }

    @Override
    public int deleteById(Integer id) {
        return picsMapper.deleteById(id);
    }

    @Override
    public List<Pics> getByType(String type) {
        return picsMapper.getByType(type);
    }
}
