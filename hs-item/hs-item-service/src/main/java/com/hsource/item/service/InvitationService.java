package com.hsource.item.service;

import com.baomidou.mybatisplus.service.IService;
import com.hsource.item.dto.reply.InsertReplyDTO;
import com.hsource.item.entity.Invitation;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 帖子 服务类
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-02
 */
public interface InvitationService extends IService<Invitation> {

    /**
     * 获取全部帖子
     *
     * @return
     */
    List<Invitation> selectList();

    /**
     * 根据id查询帖子
     * @param id
     * @return
     */
    Invitation selectInvitationById(String id);

    /**
     * 回复互动
     *
     * @param dto
     */
    void replyUser(InsertReplyDTO dto);
}
