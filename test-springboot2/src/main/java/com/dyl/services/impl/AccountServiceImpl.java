package com.dyl.services.impl;

import com.dyl.mapper.AccountMapper;
import com.dyl.pojo.Account;
import com.dyl.services.AccountService;
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
