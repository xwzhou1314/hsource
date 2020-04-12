package com.hsource.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsource.common.enums.DelFlagEnum;
import com.hsource.common.enums.ExceptionEnum;
import com.hsource.common.exception.HsException;
import com.hsource.common.utils.UuidUtil;
import com.hsource.item.constant.RedisConstant;
import com.hsource.item.dto.invitation.InvitationDTO;
import com.hsource.item.dto.invitation.InvitationPageDTO;
import com.hsource.item.dto.invitation.InvitationSearchDTO;
import com.hsource.item.dto.reply.InsertReplyDTO;
import com.hsource.item.entity.Invitation;
import com.hsource.item.mapper.InvitationMapper;
import com.hsource.item.service.InvitationService;
import com.hsource.item.service.ReplyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *   帖子 服务实现类
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-02
 */
@Service
public class InvitationServiceImpl extends ServiceImpl<InvitationMapper, Invitation> implements InvitationService {

    @Autowired
    private ReplyService replyService;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取全部帖子
     *
     * @return
     */
    @Override
    public List<Invitation> selectListByDto(InvitationSearchDTO dto) {
        return this.list(new QueryWrapper<Invitation>().eq("del_falg", DelFlagEnum.DEL_FLAG_FALSE.getCode()).like("title",dto.getTitle()));
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
        Invitation invitation = this.getById(id);
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
        Invitation invitation = this.getById(dto.getInvitationId());
        if(null == invitation){
            throw new HsException(ExceptionEnum.INVITATION_NULL);
        }
        replyService.replyUser(dto);
    }

    @Override
    public void likeNum(String id) {

        String key = RedisConstant.LIKE_NUMBER_INVITATION;
        Integer redisSum = (Integer) redisTemplate.opsForHash().get(key,id);
        if(redisSum == null){
            Invitation invitation = this.getById(id);
            if(null == invitation){
                throw new HsException(ExceptionEnum.INVITATION_NULL);
            }
            redisSum = invitation.getLikeNum();
        }
        redisSum ++;
        redisTemplate.opsForHash().put(key, id, redisSum);
    }

    @Override
    public List<Invitation> selectListHot() {
        List<Invitation> invitations = this.list(new QueryWrapper<Invitation>()
                .eq("del_falg", DelFlagEnum.DEL_FLAG_FALSE.getCode())
                .orderByDesc("like_num")
                .last("LIMIT 5"));
        return invitations;
    }

    @Override
    public List<Invitation> selectSixList() {
        List<Invitation> invitations = this.list(new QueryWrapper<Invitation>()
                .eq("del_falg", DelFlagEnum.DEL_FLAG_FALSE.getCode())
                .orderByDesc("create_date")
                .last("LIMIT 6"));
        return invitations;
    }
    @Override
    public Page<Invitation> searchListPage(InvitationPageDTO dto) {
        QueryWrapper<Invitation> wrapper = new QueryWrapper<>();
        if(null != dto && StringUtils.isNotBlank(dto.getTitle())){
            wrapper.like("title", dto.getTitle());
        }
        Page<Invitation> userPage = this.page(new Page<>(dto.getPage(), dto.getPageSize()), wrapper);
        userPage.getRecords().forEach(v->{
            if(DelFlagEnum.DEL_FLAG_FALSE.getCode().equals(v.getDelFalg())){
                v.setDelFalg(DelFlagEnum.DEL_FLAG_FALSE.getMsg());
            }else {
                v.setDelFalg(DelFlagEnum.DEL_FLAG_TRUE.getMsg());
            }
        });
        return userPage;
    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    @Override
    public Void deleteById(InvitationPageDTO dto) {
        Invitation byId = this.getOne(new QueryWrapper<Invitation>()
                .eq("del_falg", DelFlagEnum.DEL_FLAG_FALSE.getCode())
                .eq("id", dto.getId()));
        if(null == byId){
            throw new HsException(ExceptionEnum.DEL_USER_NULL);
        }
        byId.setDelFalg(DelFlagEnum.DEL_FLAG_TRUE.getCode());
        this.saveOrUpdate(byId);
        return null;
    }

    /**
     * 新增OR修改
     *
     * @param dto
     */
    @Override
    public void insertOrUpdate(InvitationDTO dto) {
        Invitation invitation = new Invitation();

        if(StringUtils.isBlank(dto.getId())){
            dto.setId(UuidUtil.get32UUID());
        }

        BeanUtils.copyProperties(dto, invitation);
        if(StringUtils.isNotBlank(dto.getId())){
            invitation.setDelFalg(DelFlagEnum.DEL_FLAG_FALSE.getCode());
        }
        invitation.setOperateDate(new Date());
        invitation.setCreateDate(new Date());
        invitation.setType("每日精选");
        invitation.setLikeNum(0);
        invitation.setReadNum(0);
        this.saveOrUpdate(invitation);
    }
}
