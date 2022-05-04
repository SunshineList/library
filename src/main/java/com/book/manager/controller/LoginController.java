package com.book.manager.controller;

import com.book.manager.entity.Users;
import com.book.manager.service.LoginService;
import com.book.manager.util.R;
import com.book.manager.util.http.CodeEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description 用户登录
 * @Date 2020/7/16 15:50
 * @Author by Tuple
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 上传地址
     */
    @Value("${file.path}")
    private String filePath;

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

    @ResponseBody
    @RequestMapping(value = "/upLoad", method = RequestMethod.POST)
    @ApiOperation("图片上传")
    public R upLoadImage(@RequestParam("file")  MultipartFile file) {

        // 获取上传文件名
        String filename = file.getOriginalFilename();
        String suffixName = filename.substring(filename.lastIndexOf("."));
        // 定义上传文件保存路径
        String path = filePath + "images/";
        //生成新的文件名称
        String newImgName = UUID.randomUUID().toString() + suffixName;
        // 新建文件
        File filepath = new File(path, newImgName);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        try {
            // 写入文件
            file.transferTo(new File(path + File.separator + newImgName));
        } catch (IOException e) {
//            return R.fail(CodeEnum.FAIL);
            e.printStackTrace();
        }
        return R.success(CodeEnum.SUCCESS, "/images/images/" + newImgName);
    }
}
