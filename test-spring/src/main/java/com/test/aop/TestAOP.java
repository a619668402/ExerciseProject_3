package com.test.aop;

import com.test.springaop.UserService;
import com.test.springaop.UserServiceImpl;

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
