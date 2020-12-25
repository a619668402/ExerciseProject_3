package com.test.testspringboot;

import com.test.testspringboot.config.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.nio.charset.StandardCharsets;

@SpringBootTest
class TestSpringbootApplicationTests {

    @Autowired
    private Person person;

//    @Autowired
//    private JavaMailSender mailSender;

    @Test
    void contextLoads() {
        System.out.println(person);
    }

    @Test
    void sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("619668402@qq.com");
        message.setTo("619668402@qq.com");
        message.setSubject("测试邮件主题");
        message.setText("这是邮件内容");
//        mailSender.send(message);
    }

    @Test
    void sendMail2() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.qq.com");
        sender.setUsername("619668402@qq.com");
        sender.setPassword("jknbwsuhnffmbbdd");
        sender.setProtocol("smtp");
        sender.setDefaultEncoding(StandardCharsets.UTF_8.name());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("619668402@qq.com");
        message.setTo("619668402@qq.com");
        message.setSubject("测试邮件主题");
        message.setText("这是邮件内容");
        sender.send(message);
    }
}
