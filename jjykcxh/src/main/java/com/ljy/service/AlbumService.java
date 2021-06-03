package com.ljy.service;

import com.ljy.VO.AlbumVO;
import com.ljy.entity.Album;

import java.util.List;

public interface AlbumService {
    List<AlbumVO> getAlbums();
    Album getAlbumById(Integer albumId);
}
