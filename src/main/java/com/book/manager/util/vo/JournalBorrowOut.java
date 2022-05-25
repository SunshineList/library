package com.book.manager.util.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JournalBorrowOut {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("期刊id")
    private Integer journalId;

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

    @ApiModelProperty("期刊名称")
    private String journalname;

    @ApiModelProperty("是否发生了续借 0 发生了 1未发生")
    private Integer isBorrow;
}
