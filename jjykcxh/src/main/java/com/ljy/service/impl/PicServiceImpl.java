package com.ljy.service.impl;

import com.ljy.dao.PicMapper;
import com.ljy.entity.Pic;
import com.ljy.entity.PicExample;
import com.ljy.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PicServiceImpl implements PicService {
    @Autowired
    PicMapper picMapper;

    @Override
    public List<Pic> getPicsByAid(Integer albumId) {
        PicExample example=new PicExample();
        PicExample.Criteria criteria = example.createCriteria();
        criteria.andAlbumIdEqualTo(albumId);
        return picMapper.selectByExample(example);
    }

    @Override
    public List<Pic> getAllPics() {
        return picMapper.selectByExample(null);
    }

    @Override
    public int deleteById(Integer id) {
        return picMapper.deleteByPrimaryKey(id);
    }

}
