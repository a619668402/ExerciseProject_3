package com.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.pojo.Account;

public interface AccountMapper extends BaseMapper<Account> {

    public Account getById(int id);
}
