package com.hsource.item.dto.invitation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xwzhou
 * @date 2020-03-24 14:11
 */
@Data
public class InvitationDTO implements Serializable{
    private static final long serialVersionUID = 5257189714262269671L;
    /**
     * id
     */
    @ApiModelProperty("id")
    private String id;
    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;
    /**
     * 帖子分类
     */
    @ApiModelProperty("帖子分类")
    private String type;

    /**
     * 图片或者视频路径
     */
    @ApiModelProperty("图片路径")
    private String attachmentUrl;
    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;
}
