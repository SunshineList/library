package com.book.manager.util.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 分页对象
 * @Date 2020/7/14 16:51
 * @Author by Tuple
 */
@Data
public class PageIn {

    /** 搜索关键字 */
    @ApiModelProperty("搜索关键字")
    private String keyword;
    
    /** 当前页 */
    @ApiModelProperty("当前页")
    private Integer currPage;

    /** 当前页条数 */
    @ApiModelProperty("每页数量")
    private Integer pageSize;

    /** 身份过滤  */
    @ApiModelProperty("身份过滤")
    private String ident;
}
