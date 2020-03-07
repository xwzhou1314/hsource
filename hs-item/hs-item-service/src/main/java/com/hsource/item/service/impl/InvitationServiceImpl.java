package com.hsource.item.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsource.common.enums.DelFlagEnum;
import com.hsource.common.enums.ExceptionEnum;
import com.hsource.common.exception.HsException;
import com.hsource.item.dto.invitation.InvitationSearchDTO;
import com.hsource.item.dto.reply.InsertReplyDTO;
import com.hsource.item.entity.Invitation;
import com.hsource.item.entity.Reply;
import com.hsource.item.mapper.InvitationMapper;
import com.hsource.item.service.InvitationService;
import com.hsource.item.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *   帖子 服务实现类
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-02
 */
@Service
@Transactional
public class InvitationServiceImpl extends ServiceImpl<InvitationMapper, Invitation> implements InvitationService {

    @Autowired
    private ReplyService replyService;

    /**
     * 获取全部帖子
     *
     * @return
     */
    @Override
    public List<Invitation> selectListByDto(InvitationSearchDTO dto) {
        return this.selectList(new EntityWrapper<Invitation>().eq("del_falg", DelFlagEnum.DEL_FLAG_FALSE.getCode()).like("",dto.getTitle()));
    }

    /**
     * 根据id查询帖子
     *
     * @param id
     * @return
     */
    @Override
    public Invitation selectInvitationById(String id) {
        // 获取帖子
        Invitation invitation = this.selectById(id);
        if(null == invitation){
            throw new HsException(ExceptionEnum.INVITATION_NULL);
        }
        // 获取评论
        invitation.setReplies(replyService.selectReplyByInvitationId(id));
        invitation.setReadNum(invitation.getReadNum() + 1);

        this.updateById(invitation);
        return invitation;
    }

    /**
     * 回复互动
     *
     * @param dto
     */
    @Override
    public void replyUser( InsertReplyDTO dto) {
        // 查询帖子是否存在
        Invitation invitation = this.selectById(dto.getInvitationId());
        if(null == invitation){
            throw new HsException(ExceptionEnum.INVITATION_NULL);
        }
        replyService.replyUser(dto);
    }

    @Override
    public void likeNum(String id) {
        Invitation invitation = this.selectById(id);
        if(null == invitation){
            throw new HsException(ExceptionEnum.INVITATION_NULL);
        }
        long likeNum = invitation.getLikeNum() + 1;
        invitation.setLikeNum(likeNum);
        this.updateById(invitation);
    }

    @Override
    public List<Invitation> selectListHot() {
        List<Invitation> invitations = this.selectList(new EntityWrapper<Invitation>()
                .eq("del_falg", DelFlagEnum.DEL_FLAG_FALSE.getCode())
                .orderBy("like_num",false)
                .last("LIMIT 5"));
        return invitations;
    }

    @Override
    public List<Invitation> selectSixList() {
        List<Invitation> invitations = this.selectList(new EntityWrapper<Invitation>()
                .eq("del_falg", DelFlagEnum.DEL_FLAG_FALSE.getCode())
                .orderBy("create_date",false)
                .last("LIMIT 6"));
        return invitations;
    }
}
