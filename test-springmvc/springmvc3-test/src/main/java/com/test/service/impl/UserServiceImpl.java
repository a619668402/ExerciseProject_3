package com.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.test.mapper.UserMapper;
import com.test.pojo.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User getById(long id) {
//        return userMapper.selectById(id);
        return userMapper.getById((int) id);
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

    public List<User> getByPage(int page) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        Page<User> selectpage = new Page<User>(page, 10);
        Page<User> result = userMapper.selectPage(selectpage, wrapper);
        return result.getRecords();
    }

    public User selectUserByUser(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("userName", user.getUserName());
        wrapper.eq("passWord", user.getPassWord());
        return userMapper.selectOne(wrapper);
    }


}
