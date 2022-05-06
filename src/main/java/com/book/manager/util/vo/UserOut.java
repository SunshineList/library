package com.book.manager.util.vo;

import com.book.manager.entity.Users;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 用户vo类
 * @Date 2020/7/24 14:29
 * @Author by Tuple
 */
@Data
public class UserOut extends Users{

    @ApiModelProperty("身份(中文：普通读者/vip读者/社会人士/超级管理员)")
    private String ident;

    @ApiModelProperty("生日：yyyy-MM-dd格式")
    private String birth;
}
