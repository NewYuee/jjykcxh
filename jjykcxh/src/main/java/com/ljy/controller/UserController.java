package com.ljy.controller;

import com.ljy.entity.Msg;
import com.ljy.entity.User;
import com.ljy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Controller
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    @ResponseBody
    public Msg signup(User user){
        if (user.getUserName()==""||user.getUserName().length()<3){
            System.out.println("请输入账号");
            String error_info="请输入账号,账号长度在3-11位之间";
            return Msg.fail().add("info",error_info);
        }
        if(user.getUserPwd()==""||user.getUserPwd().length()<6){
            System.out.println("请输入密码");
            String error_info="请输入密码，密码长度大于6";
            return Msg.fail().add("info",error_info);
        }
        if (userService.selectByUserName(user.getUserName()).size()!=0){
            String error_info="用户名已存在,请重试";
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
    public Msg signin(User user, HttpSession session, HttpServletResponse response) throws IOException {
        User user1=userService.selectOne(user);
        if (user1==null){
            String errorInfo="账号密码出错或不存在账号";
            return Msg.fail().add("errorInfo",errorInfo);
        }
        String id=user1.getUserIdentity();
        Cookie loginInfo = new Cookie("loginInfo", user1.getUserName()+"&"+user1.getUserPwd()+"&"+id);
        loginInfo.setPath("/");
        loginInfo.setMaxAge(60*60);
        if (id.equals("1")){
            response.addCookie(loginInfo);
            session.setAttribute("admin",user1);
            session.setAttribute("userInfo",user1);
            return Msg.success();
        }else if (id.equals("0")){
            response.addCookie(loginInfo);
            session.setAttribute("userInfo",user1);
            return Msg.success();
        }
        String errorInfo="未知错误，稍后再试";
        return Msg.fail().add("errorInfo",errorInfo);
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("userInfo");
        return "/index";
    }

    @RequestMapping("/user/getAlloo")
    @ResponseBody
    public Msg getAllUser(HttpServletRequest request){
        List<User> list= userService.getAll();
        if (request.getParameter("keyword")!=null){
            String kw="%"+request.getParameter("keyword")+"%";
            list=userService.getUserLikeName(kw);
        }
        int count=userService.getCount();
        return Msg.success().add("users",list).add("count",count);
    }

    @PostMapping("/user/update/{id}")
    @ResponseBody
    public Msg updateUserInfo(@PathVariable("id")Integer id, User user){
        user.setUserId(id);
        int i = userService.updateById(user);
        if (i!=0){
            return Msg.success();
        }
        return Msg.fail();
    }

    @PostMapping("/user/deleteById")
    @ResponseBody
    public Msg deleteById(@RequestParam("id") Integer id){
        int i = userService.deleteById(id);
        return Msg.success();
    }
}
