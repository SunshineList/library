package com.book.manager.controller;

import cn.hutool.core.util.StrUtil;
import com.book.manager.entity.Users;
import com.book.manager.service.LoginService;
import com.book.manager.util.R;
import com.book.manager.util.http.CodeEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 用户登录
 * @Date 2020/7/16 15:50
 * @Author by Tuple
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    @ApiOperation("用户登录")
    @PostMapping("/new_login")
    public R login(String username, String password){
        if (username.isEmpty() || password.isEmpty()){
            return R.fail(CodeEnum.NAME_OR_PASS_ERROR);
        }

        Users users = loginService.loginByUsernamePassword(username, password);

        if (users == null){
            return R.fail(CodeEnum.USER_NOT_FOUND);
        }

        return R.success(CodeEnum.SUCCESS, users);
    }
}
