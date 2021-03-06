package com.hsource.item.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hsource.common.mybatiesplus.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 帖子实体类
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-02
 */
@Data
@TableName("invitation")
public class Invitation extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 标题
     */
    @TableField("title")
    private String title;
    /**
     * 帖子分类
     */
    @TableField("type")
    private String type;
    /**
     * 是否删除 0保留 1 删除
     */
    @TableField("del_falg")
    private String delFalg;

    /**
     * 图片或者视频路径
     */
    @TableField("attachment_url")
    private String attachmentUrl;

    /**
     * 阅读数
     */
    @TableField("read_num")
    private long readNum;
    /**
     * 喜欢数
     */
    @TableField("like_num")
    private Integer likeNum;

    /**
     * 内容
     */
    @TableField("content")
    private String content;
    /**
     * 帖子评论
     */
    @TableField(exist = false)
    private List<Reply> replies;
}
