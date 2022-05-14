package com.book.manager.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "question")
public class Question {

    @ApiModelProperty("主键ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("问题1")
    private String t1;

    @ApiModelProperty("问题2")
    private String t2;

    @ApiModelProperty("问题3")
    private String t3;

    @ApiModelProperty("问题4")
    private String t4;

    @ApiModelProperty("问题5")
    private String t5;

    @ApiModelProperty("问题6")
    private String t6;

    @ApiModelProperty("问题7")
    private String t7;

    @ApiModelProperty("问题8")
    private String t8;

    @ApiModelProperty("问题9")
    private String t9;

    @ApiModelProperty("问题10")
    private String t10;

}
