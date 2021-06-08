package com.ljy.service;

import com.ljy.VO.AlbumVO;
import com.ljy.entity.Album;

import java.util.List;

public interface AlbumService {
    List<AlbumVO> getAlbums();
    Album getAlbumById(Integer albumId);

    int deleteById(Integer id);

    int updateById(Album album);

    List<Album> getAlbumsByKeyWord(String k);

    int insertOne(Album album);

    List<Album> getAlbumByName(String albumName);
}
