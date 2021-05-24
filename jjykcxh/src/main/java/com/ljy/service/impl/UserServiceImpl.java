package com.ljy.service.impl;

import com.ljy.dao.UserMapper;
import com.ljy.entity.User;
import com.ljy.entity.UserExample;
import com.ljy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public boolean del(int id) {
        return false;
    }

    @Override
    public User selectOne(User user) {
        return userMapper.selectOne(user);
    }

    @Override
    public int insertOne(User user) {
        int i = userMapper.insertSelective(user);
        return i;
    }

    @Override
    public List<User> selectByUserName(String username) {
        UserExample example=new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(username);
        return userMapper.selectByExample(example);
    }
}
