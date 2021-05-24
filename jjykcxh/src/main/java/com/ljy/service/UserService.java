package com.ljy.service;

import com.ljy.entity.User;

import java.util.List;

public interface UserService {
    
    boolean del(int id);
    User selectOne(User user);
    int insertOne(User user);
    List<User> selectByUserName(String username);
}
