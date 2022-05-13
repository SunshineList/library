package com.book.manager.util.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class BookTjOut {
    @ApiModelProperty("马克思主义、列宁主义、毛泽东思想、邓小平理论")
    private Integer sx;

    @ApiModelProperty("哲学、宗教")
    private Integer zx;

    @ApiModelProperty("文化、科学、教育、体育")
    private Integer wh;

    @ApiModelProperty("政治、法律")
    private Integer zf;

    @ApiModelProperty("历史、地理")
    private Integer ld;
}
