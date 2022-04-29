package com.book.manager.service;

import com.book.manager.dao.UsersMapper;
import com.book.manager.entity.Users;
import com.book.manager.repos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 用户登录
     */


    public Users loginByUsernamePassword(String username, String password) {
        return usersRepository.findByUsernameAndPassword(username, password);
    }

}
