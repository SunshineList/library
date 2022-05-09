package com.book.manager.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description 用户实体类
 * @Date 2020/7/14 15:39
 * @Author by Tuple
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "users")
public class Users {

    @ApiModelProperty("主键ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("是否为超级管理员")
    private Integer isAdmin;

    @ApiModelProperty("电话")
    private String tel;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("可借数量")
    private Integer size;

    @ApiModelProperty("期刊可借数量")
    private Integer qkSize;

    @ApiModelProperty("身份：0 普通读者,1 vip读者,2 图书超级管理员,3 超级管理员,4 采购员")
    private Integer identity;

    // json： {"id":id,"avatar":avatar,"nickname":nickname,"username":username,"password":password,"birthday":birthday,"isAdmin":isAdmin,"tel":tel
    // ,"email":email,"address":address,"size":size,"identity":identity}
}
