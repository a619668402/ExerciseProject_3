package com.dyl.service;

import com.dyl.pojo.User;
import java.util.List;

public interface UserService {

    public User getById(long id);

    public void insert(User user);

    public List<User> getAll();

    public void deleteById(long id);

    public List<User> getByPage(int page);

    public User selectUserByUser(User user);
}
