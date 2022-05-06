package com.book.manager.util.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 采购记录
 */
@Data
public class PurchaseOut {

    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("书籍名称")
    private String bookname;

    @ApiModelProperty("书籍类型")
    private String bookType;

    @ApiModelProperty("数量")
    private Integer num;

    @ApiModelProperty("价格")
    private double price;

    @ApiModelProperty("isbn编码")
    private String isbn;

    @ApiModelProperty("采购理由")
    private String reason;

    @ApiModelProperty("状态  0 审核通过 1审核不通过")
    private String status;


}
