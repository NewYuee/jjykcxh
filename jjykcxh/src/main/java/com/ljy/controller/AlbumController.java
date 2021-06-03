package com.ljy.controller;

import com.ljy.VO.AlbumVO;
import com.ljy.VO.PicVO;
import com.ljy.entity.Album;
import com.ljy.entity.AlbumJson;
import com.ljy.entity.Msg;
import com.ljy.entity.Pic;
import com.ljy.service.AlbumService;
import com.ljy.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @Autowired
    PicService picService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Msg getAll(){
        List<AlbumVO> list = albumService.getAlbums();
        return Msg.success().add("albums",list);
    }

    @RequestMapping("/getPicsByAid/{albumId}")
    @ResponseBody
    public AlbumJson getPicsByAid(@PathVariable("albumId") Integer albumId){
        Album album=albumService.getAlbumById(albumId);
        List<Pic> picList=picService.getPicsByAid(albumId);
        List<PicVO> picVOList=new ArrayList<>();
        for (Pic pic:picList){
            String src="http://120.78.196.234/resource/jjykcxh/album/"+pic.getPicName();
            PicVO picVO=new PicVO(pic.getPicName(),pic.getPicId(),src,null);
            picVOList.add(picVO);
        }

        AlbumJson albumJson=new AlbumJson(album.getAlbumName(),albumId,0,picVOList);
        return albumJson;
    }

    @RequestMapping("/index")
    public String toalbum(){
        return "albums";
    }
}
