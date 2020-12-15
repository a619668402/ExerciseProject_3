package com.dyl.aop;

import com.dyl.springaop.UserService;

public class UserServiceProxy implements UserService {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void add() {
        log("add");
        userService.add();
    }

    @Override
    public void update() {
        log("update");
        userService.update();
    }

    @Override
    public void delete() {
        log("delete");
        userService.delete();
    }

    @Override
    public void edit() {
        log("edit");
        userService.edit();
    }

    public void log(String msg) {
        System.out.println("[debug] com.dyl.aop " + msg);
    }
}
