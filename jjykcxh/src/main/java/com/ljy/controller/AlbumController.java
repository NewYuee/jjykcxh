package com.ljy.controller;

import com.ljy.VO.AlbumVO;
import com.ljy.entity.Album;
import com.ljy.entity.Msg;
import com.ljy.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Msg getAll(){
        List<AlbumVO> list = albumService.getAlbums();
        return Msg.success().add("albums",list);
    }
}
