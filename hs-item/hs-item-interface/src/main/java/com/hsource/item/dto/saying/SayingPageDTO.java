package com.hsource.item.dto.saying;

import com.hsource.common.utils.SplitPageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xwzhou
 * @date 2020-03-23 23:10
 */
@Data
public class SayingPageDTO extends SplitPageDTO implements Serializable {
    private static final long serialVersionUID = 1131474695219996112L;
    @ApiModelProperty("搜索条件")
    private String content;

    @ApiModelProperty("id")
    private String id;
}
