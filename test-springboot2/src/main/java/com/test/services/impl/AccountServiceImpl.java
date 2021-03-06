package com.test.services.impl;

import com.test.mapper.AccountMapper;
import com.test.pojo.Account;
import com.test.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account getById() {
//        QueryWrapper<Account> wrapper = new QueryWrapper<>();
//        wrapper.eq("id", 110);
//        Account account = accountMapper.selectOne(wrapper);
//        return account;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Account account = accountMapper.getById(120);
        return account;
    }
}
