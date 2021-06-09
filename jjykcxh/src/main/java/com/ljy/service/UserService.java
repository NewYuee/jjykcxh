package com.ljy.service;

import com.ljy.entity.User;

import java.util.List;

public interface UserService {
    
    User selectOne(User user);
    int insertOne(User user);
    List<User> selectByUserName(String username);
    User getUserById(Integer id);

    List<User> getAll();

    int getCount();

    int updateById(User user);

    int deleteById(Integer id);

    List<User> getUserLikeName(String keyword);
}
