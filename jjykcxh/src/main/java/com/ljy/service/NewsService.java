package com.ljy.service;

import com.ljy.entity.News;

import java.util.List;

public interface NewsService {

    List<News> getNews();

    News getNewsById(Integer id);

    int getNewsCount();

    int deleteById(Integer id);

    int updateById(News news);

    int insertOne(News news);

    List<News> getNewsByKeyWord(String k);
}
