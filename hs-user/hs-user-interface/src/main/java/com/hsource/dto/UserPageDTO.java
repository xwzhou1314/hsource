package com.hsource.dto;

import com.hsource.common.utils.SplitPageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xwzhou
 * @date 2020-03-08 16:55
 */
@Data
public class UserPageDTO extends SplitPageDTO {

    @ApiModelProperty("搜索条件")
    private String search;

    @ApiModelProperty("id")
    private String id;
}
