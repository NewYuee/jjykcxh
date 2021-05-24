package com.ljy.controller;

import com.ljy.entity.User;
import com.ljy.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserControllerTest {
    @Autowired
    UserService userService;

    @Test
    public void signup() {
        User user=new User();
        user.setUserName("nihao11");
        user.setUserPwd("cjfq");
        System.out.println(userService.insertOne(user));
    }
}
