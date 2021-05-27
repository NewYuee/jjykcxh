package com.ljy.service.impl;

import com.ljy.VO.AlbumVO;
import com.ljy.dao.AlbumMapper;
import com.ljy.dao.CommentMapper;
import com.ljy.dao.PicMapper;
import com.ljy.entity.Album;
import com.ljy.entity.Comment;
import com.ljy.entity.Pic;
import com.ljy.entity.PicExample;
import com.ljy.service.AlbumService;
import com.ljy.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        for (Album album:albumList){
            AlbumVO albumVO=new AlbumVO();
            BeanUtils.copyProperties(album,albumVO);
            PicExample example=new PicExample();
            PicExample.Criteria criteria = example.createCriteria();
            criteria.andAlbumIdEqualTo(album.getAlbumId());
            List<Pic> pics = picMapper.selectByExample(example);
            List<Comment> comments=commentService.getCommentsByAlbumId(album.getAlbumId());
            albumVO.setC_count(comments.size());
            albumVO.setPics(pics);
            albumVOList.add(albumVO);
        }
        return albumVOList;
    }
}
