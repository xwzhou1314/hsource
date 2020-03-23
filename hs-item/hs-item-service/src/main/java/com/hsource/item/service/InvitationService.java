package com.hsource.item.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsource.item.dto.invitation.InvitationPageDTO;
import com.hsource.item.dto.invitation.InvitationSearchDTO;
import com.hsource.item.dto.reply.InsertReplyDTO;
import com.hsource.item.entity.Invitation;

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
    List<Invitation> selectListByDto(InvitationSearchDTO dto);

    /**
     * 根据id查询帖子
     *
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

    /**
     * 点赞人数
     *
     * @param id
     */
    void likeNum(String id);

    /**
     * 热门帖子
     *
     * @return
     */
    List<Invitation> selectListHot();

    /**
     * 前六个帖子
     *
     * @return
     */
    List<Invitation> selectSixList();

    /**
     * 分页获取帖子
     *
     * @param dto
     * @return
     */
    Page<Invitation> searchListPage(InvitationPageDTO dto);

    /**
     * 删除
     * @param dto
     * @return
     */
    Void deleteById(InvitationPageDTO dto);
}
