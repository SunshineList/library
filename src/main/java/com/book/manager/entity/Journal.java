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
 * @Description 期刊实体类
 * @Date 2020/7/14 15:58
 * @Author by Tuple
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "journal")
public class Journal {
    @ApiModelProperty("主键")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("图书issn编码")
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
    private Date publishTime;

}
