package com.dyl.service.impl;

import com.dyl.mapper.UserMapper;
import com.dyl.pojo.User;
import com.dyl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User getById(long id) {
        return userMapper.selectById(id);
    }

    public void insert(User user) {
        userMapper.insert(user);
//        int i = 1 / 0;
    }

    public List<User> getAll() {
        return userMapper.selectList(null);
    }

    public void deleteById(long id) {
        userMapper.deleteById(id);
    }
}
