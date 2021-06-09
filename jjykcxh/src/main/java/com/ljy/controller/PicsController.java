package com.ljy.controller;

import com.ljy.entity.Msg;
import com.ljy.entity.Pics;
import com.ljy.service.PicsService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/pics")
public class PicsController {
    @Autowired
    PicsService picsService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Msg getAll(){
        return Msg.success().add("pics",picsService.getAll()).add("count",picsService.getAll().size());
    }

    @RequestMapping("/update/{id}")
    @ResponseBody
    public Msg updateById(@PathVariable("id")Integer id,Pics pics){
        Pics pc=picsService.getOneById(id);
        pc.setType(pics.getType());
        int i = picsService.updateById(pc);
        if (i!=0){
            return Msg.success();
        }
        return Msg.fail();
    }
    @PostMapping("/deleteById")
    @ResponseBody
    public Msg deleteById(@RequestParam("id") Integer id){
        int i = picsService.deleteById(id);
        if (i!=0){
            return Msg.success();
        }
        return Msg.fail();
    }

    @PostMapping("/upload")
    @ResponseBody
    public Msg uploadInAlbum(@RequestParam("file") MultipartFile file) throws UnsupportedEncodingException {
        //1.定义上传文件服务器的路径
        String path="http://120.78.196.234/resource/jjykcxh/pics/";
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

        Pics pics=new Pics();
        pics.setPname(encodeFileName);
        pics.setSrc(path + encodeFileName);
        pics.setType("1");
        int i=picsService.insertOne(pics);
        if (i==0){
            return Msg.fail();
        }
        return Msg.success();
    }

    @RequestMapping("/getPicsByType")
    @ResponseBody
    public Msg getByType(@RequestParam("type")String type){
        List<Pics> picsList =picsService.getByType(type);
        return Msg.success().add("pics",picsList);
    }

}
