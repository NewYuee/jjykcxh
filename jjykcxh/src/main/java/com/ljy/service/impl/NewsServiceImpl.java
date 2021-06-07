package com.ljy.service.impl;

import com.ljy.dao.NewsMapper;
import com.ljy.entity.News;
import com.ljy.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsMapper newsMapper;

    @Override
    public List<News> getNews() {
        List<News> list=newsMapper.selectByExample(null);
        return list;
    }

    @Override
    public News getNewsById(Integer id) {
        return newsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int getNewsCount() {
        return newsMapper.countByExample(null);
    }

    @Override
    public int deleteById(Integer id) {
        return newsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(News news) {
        return newsMapper.updateByPrimaryKeyWithBLOBs(news);
    }

    @Override
    public int insertOne(News news) {
        int insert = newsMapper.insert(news);
        return insert;
    }
}
