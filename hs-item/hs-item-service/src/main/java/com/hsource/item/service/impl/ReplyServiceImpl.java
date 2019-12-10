package com.hsource.item.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsource.common.enums.ExceptionEnum;
import com.hsource.common.exception.HsException;
import com.hsource.item.client.UserClient;
import com.hsource.item.dto.reply.InsertReplyDTO;
import com.hsource.item.entity.Reply;
import com.hsource.item.mapper.ReplyMapper;
import com.hsource.item.service.InvitationService;
import com.hsource.item.service.ReplyService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-08
 */
@Service
@Transactional
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements ReplyService {


    @Autowired
    private UserClient userClient;
    /**
     * 根据帖子查询评论
     *
     * @param invitationId
     * @return
     */
    @Override
    public List<Reply> selectReplyByInvitationId(String invitationId) {

        // 1.获取当前帖子下所有评论
        List<Reply> replies = this.selectList(new EntityWrapper<Reply>()
                .eq("invitation_id", invitationId));

        // 2.获取评论区内的互动回复
        replies.forEach(v -> {
            v.setReplies(this.selectList(new EntityWrapper<Reply>()
                    .eq("last_id", invitationId)
                    .orderBy("operate_date" , false)));
        });
        return replies;
    }

    /**
     * 回复互动
     *
     * @param dto
     */
    @Override
    public void replyUser(InsertReplyDTO dto) {
        // 查询用户是否存在
        if(userClient.selectUserById(dto.getUserId())){
            throw new HsException(ExceptionEnum.REPLY_FALSE);
        }
        // 如果 invitationId 的 id 为 0 示为评论互动
        if("0".equals(dto.getInvitationId())){
            // 查询回复的评论是否存在
            if(null == this.selectById(dto.getLastId())){
                throw new HsException(ExceptionEnum.REPLY_FALSE);
            }
        }
        Reply reply = new Reply();
        BeanUtils.copyProperties(dto, reply);

        this.insert(reply);
    }
}
