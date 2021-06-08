package com.ljy.service.impl;

import com.ljy.dao.NoticeMapper;
import com.ljy.entity.Notice;
import com.ljy.entity.NoticeExample;
import com.ljy.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public List<Notice> getAll() {
        return noticeMapper.selectByExample(null);
    }

    @Override
    public Notice getNoticeById(Integer id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteById(Integer id) {
        return noticeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(Notice notice) {
        return noticeMapper.updateByPrimaryKey(notice);
    }

    @Override
    public int insertOne(Notice notice) {
        return noticeMapper.insert(notice);
    }

    @Override
    public List<Notice> getNewsByKeyWord(String k) {
        NoticeExample example=new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria();
        criteria.andNoticeTitleLike(k);
        return noticeMapper.selectByExample(example);
    }
}
