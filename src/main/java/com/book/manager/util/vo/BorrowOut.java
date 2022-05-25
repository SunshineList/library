package com.book.manager.util.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 借阅日志记录
 */
@Data
public class BorrowOut {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("图书id")
    private Integer bookId;

    @ApiModelProperty("借阅时间")
    private String createTime;

    @ApiModelProperty("归还时间")
    private String endTime;

    @ApiModelProperty("实际归还时间")
    private String updateTime;

    @ApiModelProperty("是否归还? 0 已归还/1 未归还")
    private Integer ret;

    @ApiModelProperty("用户姓名")
    private String username;

    @ApiModelProperty("图书名称")
    private String bookname;

    @ApiModelProperty("是否续借")
    private Integer isBorrow;

}
