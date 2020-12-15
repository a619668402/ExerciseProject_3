package com.dyl.aop;

import com.dyl.springaop.UserService;
import com.dyl.springaop.UserServiceImpl;

public class TestAOP {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.add();

        UserServiceProxy userServiceProxy = new UserServiceProxy();
        userServiceProxy.setUserService(userService);
        userServiceProxy.add();
        userServiceProxy.delete();
    }
}
