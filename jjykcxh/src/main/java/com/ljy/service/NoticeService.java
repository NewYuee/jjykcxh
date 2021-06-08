package com.ljy.service;

import com.ljy.entity.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> getAll();
    Notice getNoticeById(Integer id);

    int deleteById(Integer id);

    int updateById(Notice notice);

    int insertOne(Notice notice);

    List<Notice> getNewsByKeyWord(String k);
}
