package com.ljy.controller;

import com.ljy.entity.Msg;
import com.ljy.entity.Pic;
import com.ljy.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/pic")
public class PicController {
    @Autowired
    PicService picService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Msg getAll(HttpServletRequest request){
        if (request.getParameter("keyword")!=null){
            String key=request.getParameter("keyword");
            int id=Integer.parseInt(key);
            List<Pic> listPic=picService.getPicsByAid(id);
            return Msg.success().add("pics",listPic).add("count",listPic.size());
        }
        List<Pic> list=picService.getAllPics();
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
}
