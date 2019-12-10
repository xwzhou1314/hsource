package com.hsource.item.dto.reply;

import lombok.Data;

/**
 * 增加评论 dto
 */
@Data
public class InsertReplyDTO {
    /**
     * 帖子 id 为 0 时表示为评论互动
     */
    private String invitationId;
    /**
     * 用户表id
     */
    private String userId;
    /**
     * 回复内容
     */
    private String text;
    /**
     * 上一条回复id （用于关联回复表，维护好每条评论的关系）
     */
    private Integer lastId;
}
