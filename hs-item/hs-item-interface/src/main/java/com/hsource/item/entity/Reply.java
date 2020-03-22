package com.hsource.item.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hsource.common.mybatiesplus.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * <p>
 *  评论
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-08
 */
@Data
@TableName("reply")
public class Reply extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 帖子 id
     */
    @TableField("invitation_id")
    private String invitationId;
    /**
     * 用户表id
     */
    @TableField("user_id")
    private String userId;
    /**
     * 回复内容 
     */
    @TableField("text")
    private String text;
    /**
     * 上一条回复id （用于关联回复表，维护好每条评论的关系）
     */
    @TableField("last_id")
    private Integer lastId;

    @TableField(exist = false)
    private List<Reply> replies;
}
