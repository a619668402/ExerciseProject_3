package com.dyl.controller;

import com.dyl.pojo.Account;
import com.dyl.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("test")
    public String test() {
        Account account = accountService.getById();
        System.out.println(account);
        return "test";
    }

    @Async
    @RequestMapping("test1")
    @ResponseBody
    public Account getAccountById() {
        Account account = accountService.getById();
        System.out.println(account);
        return account;
    }
}
