package com.book.manager.util.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JournalBackOut extends JournalOut {

    @ApiModelProperty("借阅时间")
    private String borrowTime;

    @ApiModelProperty("应还时间")
    private String endTime;

    @ApiModelProperty("是否逾期")
    private String late;

    @ApiModelProperty("是否已续借")
    private String isBorrow;

}
