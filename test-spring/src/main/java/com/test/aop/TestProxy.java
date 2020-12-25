package com.test.aop;

import com.test.springaop.UserService;
import com.test.springaop.UserServiceImpl;

public class TestProxy {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        // 动态代理: Proxy 创建动态代理对象, InvocationHandler 调用处理程序并返回处理结果
        ProxyInvocationHandler handler = new ProxyInvocationHandler();
        handler.setUserService(userService);
        UserService proxy = (UserService) handler.getProxy();
        proxy.edit();

    }
}
