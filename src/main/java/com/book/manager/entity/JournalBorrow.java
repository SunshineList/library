package com.book.manager.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "journal_borrow")
public class JournalBorrow {

    @ApiModelProperty("主键ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("期刊ID")
    private Integer journalId;

    @ApiModelProperty("借阅时间")
    private Date createTime;

    @ApiModelProperty("归还时间")
    private Date endTime;

    @ApiModelProperty("实际归还时间")
    private Date updateTime;

    @ApiModelProperty("是否归还? 0 已归还/1 未归还")
    private Integer ret;

    @ApiModelProperty("用户姓名")
    private String username;

    @ApiModelProperty("期刊名称")
    private String journalname;

}
