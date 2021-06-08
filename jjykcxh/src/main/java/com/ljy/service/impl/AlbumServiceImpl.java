package com.ljy.service.impl;

import com.ljy.VO.AlbumVO;
import com.ljy.dao.AlbumMapper;
import com.ljy.dao.CommentMapper;
import com.ljy.dao.PicMapper;
import com.ljy.entity.*;
import com.ljy.service.AlbumService;
import com.ljy.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumMapper albumMapper;

    @Autowired
    CommentService commentService;

    @Autowired
    PicMapper picMapper;

    @Override
    public List<AlbumVO> getAlbums() {
        List<Album> albumList=albumMapper.selectByExample(null);
        List<AlbumVO> albumVOList=new ArrayList<>();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Album album:albumList){
            AlbumVO albumVO=new AlbumVO();
            BeanUtils.copyProperties(album,albumVO);
            PicExample example=new PicExample();
            PicExample.Criteria criteria = example.createCriteria();
            criteria.andAlbumIdEqualTo(album.getAlbumId());
            List<Pic> pics = picMapper.selectByExample(example);
            List<Comment> comments=commentService.getCommentsInAlbum(album.getAlbumId());
            albumVO.setC_count(comments.size());
            albumVO.setUpdateTime(df.format(album.getUpdateTime()));
            albumVO.setPics(pics);
            albumVOList.add(albumVO);
        }
        return albumVOList;
    }

    @Override
    public Album getAlbumById(Integer albumId) {
        return albumMapper.selectByPrimaryKey(albumId);
    }

    @Override
    public int deleteById(Integer id) {
        return albumMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(Album album) {
        return albumMapper.updateByPrimaryKey(album);
    }

    @Override
    public List<Album> getAlbumsByKeyWord(String k) {
        AlbumExample example=new AlbumExample();
        AlbumExample.Criteria criteria = example.createCriteria();
        criteria.andAlbumNameLike(k);
        return albumMapper.selectByExample(example);
    }

    @Override
    public int insertOne(Album album) {
        return albumMapper.insert(album);
    }

    @Override
    public List<Album> getAlbumByName(String albumName) {
        AlbumExample example=new AlbumExample();
        AlbumExample.Criteria criteria = example.createCriteria();
        criteria.andAlbumNameEqualTo(albumName);
        return albumMapper.selectByExample(example);
    }
}
