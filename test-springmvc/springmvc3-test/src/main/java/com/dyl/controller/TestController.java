package com.dyl.controller;

import com.dyl.pojo.User;
import com.dyl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("test")
    public String test() {
        return "test";
    }

    @RequestMapping("getone/{id}")
    @ResponseBody
    public User getOne(@PathVariable long id) {
        User user = userService.getById(id);
        System.out.println("===========" + user);
        return user;
    }

    @RequestMapping("add")
    @ResponseBody
    public String add() {
        User user = new User();
        user.setUserName("bbb");
        user.setPassWord("123");
        userService.insert(user);
        return "success";
    }
}
