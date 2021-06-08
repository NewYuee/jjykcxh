package com.ljy.controller;

import com.ljy.VO.AlbumVO;
import com.ljy.VO.PicVO;
import com.ljy.entity.*;
import com.ljy.service.AlbumService;
import com.ljy.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public Msg getAll(HttpServletRequest request){
        if(request.getParameter("keyword")!=null){
            String k="%"+request.getParameter("keyword")+"%";
            List<Album> list=albumService.getAlbumsByKeyWord(k);
            return Msg.success().add("albums",list).add("count",list.size());
        }
        List<AlbumVO> list = albumService.getAlbums();
        return Msg.success().add("albums",list).add("count",list.size());
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

    @PostMapping("/deleteById")
    @ResponseBody
    public Msg deleteById(@RequestParam("id") Integer id){
        int i = albumService.deleteById(id);
        if(i!=0){
            return Msg.success();
        }
        return Msg.fail();
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public Msg updateById(@PathVariable("id") Integer id,Album album) throws ParseException {
        album.setAlbumId(id);
        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        album.setUpdateTime(df.parse(df.format(date)));
        int i=albumService.updateById(album);
        if(i!=0){
            return Msg.success();
        }
        return Msg.fail();
    }

    @PostMapping("/insertOne")
    @ResponseBody
    public Msg insertOne(Album album) throws ParseException {
        if(album.getAlbumName()==null||album.getAuthorName()==null){
            return Msg.fail();
        }
        List<Album> albumByName=albumService.getAlbumByName(album.getAlbumName());
        if(albumByName.size()>0){
            String ms="相册已存在，请换个名字吧";
            return Msg.fail().add("info",ms);
        }
        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        album.setUpdateTime(df.parse(df.format(date)));
        int i=albumService.insertOne(album);
        if(i!=0){
            return Msg.success();
        }
        return Msg.fail();
    }


}
