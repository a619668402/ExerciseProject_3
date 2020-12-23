package com.dyl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyl.pojo.Account;

public interface AccountMapper extends BaseMapper<Account> {

    public Account getById(int id);
}
