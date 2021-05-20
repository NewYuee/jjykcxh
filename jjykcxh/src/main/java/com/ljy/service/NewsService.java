package com.ljy.service;

import com.ljy.entity.News;

import java.util.List;

public interface NewsService {

    List<News> getNews();

    News getNewsById(Integer id);
}
