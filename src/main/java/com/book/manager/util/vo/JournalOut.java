package com.book.manager.util.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 期刊出参对象
 */
@Data
public class JournalOut {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("期刊issn编码")
    private String issn;

    @ApiModelProperty("期刊名称")
    private String name;

    @ApiModelProperty("期刊作者")
    private String author;

    @ApiModelProperty("期刊页数")
    private Integer pages;

    @ApiModelProperty("单价")
    private Double price;

    @ApiModelProperty("库存")
    private Integer size;

    @ApiModelProperty("出版社")
    private String publish;

    @ApiModelProperty("出版时间")
    private String publishTime;

}
