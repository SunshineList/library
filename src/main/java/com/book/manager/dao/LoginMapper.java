package com.book.manager.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LoginMapper {
    /**
     * 用户登录
     */

    int loginByUsername(String username, String password);
}
