package com.book.manager.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


/**
 *
 * 采购表
 *
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "purchase")
public class Purchase {

    @ApiModelProperty("主键ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
