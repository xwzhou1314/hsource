package com.hsource.item.dto.saying;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xwzhou
 * @date 2020-03-24 13:42
 */
@Data
public class SayingDTO implements Serializable {

    private static final long serialVersionUID = 2027026042152632458L;
    /**
     * id
     */
    @ApiModelProperty("id")
    private String id;

    /**
     * 语录
     */
    @ApiModelProperty("语录")
    private String content;


    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Long sort;

    /**
     * 颜色
     */
    @ApiModelProperty("颜色")
    private String color;
}
