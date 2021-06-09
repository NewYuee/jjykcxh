package com.ljy.controller;

import com.ljy.entity.Pics;
import com.ljy.entity.User;
import com.ljy.service.PicsService;
import com.ljy.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserControllerTest {
    @Autowired
    UserService userService;
    @Autowired
    PicsService picsService;

    @Test
    public void testGetPics(){
        System.out.println(picsService.getOneById(20210609));
    }

    @Test
    public void testInsertPics(){
        Pics pics=new Pics();
        pics.setSrc("45fqf");
        pics.setPname("pname");
        pics.setType("1");
        int i =picsService.insertOne(pics);
        System.out.println(i);

    }

    @Test
    public void testGetPicsByType(){
        String type="1";
        List<Pics> byType = picsService.getByType(type);
        System.out.println(byType.size());
    }

    @Test
    public void signup() {
        User user=new User();
        user.setUserName("nihao11");
        user.setUserPwd("cjfq");
        System.out.println(userService.insertOne(user));
    }

    @Test
    public void testTime(){
        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(df.format(date));

    }
}
