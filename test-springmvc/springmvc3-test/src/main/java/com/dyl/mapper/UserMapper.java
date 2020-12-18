package com.dyl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyl.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    User getById(@Param("id") int id);
}
