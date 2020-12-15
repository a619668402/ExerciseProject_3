package com.dyl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Hello hello = ((Hello) context.getBean("hello"));
        System.out.println(hello);
        System.out.println(hello.getMap().get("name"));

        Hello hello1 = (Hello) context.getBean("hello1");
        System.out.println(hello1);

        System.out.println(context.getBean("hello2"));

    }

}
