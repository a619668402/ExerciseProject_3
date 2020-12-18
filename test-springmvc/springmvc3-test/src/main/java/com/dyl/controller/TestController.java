package com.dyl.controller;

import com.dyl.pojo.User;
import com.dyl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping("getAll")
    public String getAll(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "test";
    }

    @RequestMapping("getByPage/{page}")
    public String getByPage(@PathVariable("page") int page, Model model) {
        List<User> users = userService.getByPage(page);
        model.addAttribute("users", users);
        return "test";
    }

    @RequestMapping(value = "login.tips", method = RequestMethod.POST)
    @ResponseBody
    public String login(User user) {
        User user1 = userService.selectUserByUser(user);
        if (user1 != null) {
            return "success";
        } else {
            return "failure";
        }
    }
}
