package com.ljy.controller;

import com.ljy.entity.Msg;
import com.ljy.entity.User;
import com.ljy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//import javax.annotation.Resource;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    @ResponseBody
    public Msg signup(User user){
        if (user.getUserName()==""){
            System.out.println("请输入账号");
            String error_info="请输入账号";
            return Msg.fail().add("info",error_info);
        }
        if(user.getUserPwd()==""){
            System.out.println("请输入密码");
            String error_info="请输入密码";
            return Msg.fail().add("info",error_info);
        }
        if (userService.selectByUserName(user.getUserName()).size()!=0){
            String error_info="账户已存在,请重试";
            System.out.println(userService.selectByUserName(user.getUserName()));
            System.out.println(error_info);
            return Msg.fail().add("info",error_info);
        }
        userService.insertOne(user);
        String info="恭喜！注册成功，可立即登录";
        return Msg.success().add("info",info);
    }

    @PostMapping("/signin")
    @ResponseBody
    public Msg signin(User user,HttpSession session) throws IOException {
        User user1=userService.selectOne(user);
        if (user1!=null){
            session.setAttribute("userInfo",user1);
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("userInfo");
        return "/index";
    }
}
