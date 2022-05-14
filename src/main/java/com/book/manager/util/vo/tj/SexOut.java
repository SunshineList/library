package com.book.manager.util.vo.tj;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SexOut {
    @ApiModelProperty("男")
    private Integer male;

    @ApiModelProperty("女")
    private Integer female;

}
