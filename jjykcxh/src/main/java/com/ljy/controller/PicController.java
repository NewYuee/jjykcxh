package com.ljy.controller;

import com.ljy.entity.Album;
import com.ljy.entity.Msg;
import com.ljy.entity.Pic;
import com.ljy.service.AlbumService;
import com.ljy.service.PicService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/pic")
public class PicController {
    @Autowired
    PicService picService;

    @Autowired
    AlbumService albumService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Msg getAll(@RequestParam(value = "kw",required = false)Integer id){
        List<Pic> list=picService.getAllPics();
        if (id!=null){
            list=picService.getPicsByAid(id);
        }
        return Msg.success().add("pics",list).add("count",list.size());

    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public Msg deleteById(@RequestParam("id")Integer id){
        int i=picService.deleteById(id);
        if (i!=0){
            return Msg.success();
        }
        return Msg.fail();
    }

    @PostMapping("/uploadInAlbum/{albumId}")
    @ResponseBody
    public Msg uploadInAlbum(@RequestParam("file")MultipartFile file, @PathVariable("albumId") Integer aid) throws UnsupportedEncodingException {
        if (albumService.getAlbumById(aid)==null){
            return Msg.fail();
        }
        //1.定义上传文件服务器的路径
        String path="http://120.78.196.234/resource/jjykcxh/album/";
        //获取上传文件的名称
        String originalFilename = file.getOriginalFilename();
        String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().substring(0,10).replace("-", "");
        String filename=uuid+suffix;
        //2.1.创建客户端的对象
        Client client = Client.create();
        String encodeFileName = URLEncoder.encode(filename,"UTF-8");
        System.out.println(encodeFileName);
        //3.和图片服务器进行连接,拿到一个web资源
        WebResource webResource = client.resource(path + encodeFileName);
        if(webResource==null){
            return Msg.fail();
        }
        //4.完成文件上传(getBytes以字节方式传)
        try {
            webResource.put(file.getBytes());
        } catch (IOException e) {
            return Msg.fail();
        }
        System.out.println("文件上传成功<========="+encodeFileName);

        Pic pic=new Pic();
        pic.setPicName(encodeFileName);
        pic.setAlbumId(aid);
        int i=picService.insertOne(pic);
        if (i==0){
            return Msg.fail();
        }
        return Msg.success();
    }
}
