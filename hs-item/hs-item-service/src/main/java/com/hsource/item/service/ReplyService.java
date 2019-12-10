package com.hsource.item.service;

import com.baomidou.mybatisplus.service.IService;
import com.hsource.item.dto.reply.InsertReplyDTO;
import com.hsource.item.entity.Reply;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-08
 */
public interface ReplyService extends IService<Reply> {
    /**
     * 根据帖子查询评论
     * @param invitationId
     * @return
     */
    List<Reply> selectReplyByInvitationId(String invitationId);

    /**
     * 回复互动
     *
     * @param dto
     */
    void replyUser(InsertReplyDTO dto);
}
