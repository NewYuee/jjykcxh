package com.ljy.controller;


import com.ljy.entity.Msg;
import com.ljy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//import javax.annotation.Resource;

@RequestMapping("/user")
@Controller
public class UserController {


    @GetMapping("/list")
    @ResponseBody
    public Msg list(){
        return Msg.success();
    }

    @GetMapping("/add")
    public String add(){return "add_user";}

    @RequestMapping("/listPage")
    public ModelAndView listPage(){
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("menu");
        return modelAndView;
    }
}
