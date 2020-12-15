package com.dyl.springaop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
//        UserService userService = (UserService) context.getBean("userService");
//        userService.add();
//
//        System.out.println("-----------------------");
//
//        UserService userService1 = ((UserService) context.getBean("userService1"));
//        userService1.add();
//        userService1.edit();
//
//        System.out.println("-----------------------");

        UserService userService2 = ((UserService) context.getBean("userService2"));
        userService2.add();
        userService2.update();
    }
}
