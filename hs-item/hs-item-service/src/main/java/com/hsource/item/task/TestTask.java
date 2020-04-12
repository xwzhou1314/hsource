package com.hsource.item.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsource.common.enums.DelFlagEnum;
import com.hsource.item.constant.RedisConstant;
import com.hsource.item.entity.Invitation;
import com.hsource.item.service.InvitationService;
import com.hsource.item.service.ReplyService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author xwzhou
 * @date 2020-03-20 10:55
 */
@Slf4j
@Component
@EnableScheduling
@JobHandler(value = "TestTask")
public class TestTask extends IJobHandler {

    @Autowired
    private InvitationService invitationService;

    @Resource
    RedisTemplate<String, Object> redisTemplate;



    @Override
    public ReturnT<String> execute(String s) throws Exception {

        List<Invitation> list = invitationService.list(new QueryWrapper<Invitation>()
                .eq("del_falg", DelFlagEnum.DEL_FLAG_FALSE.getCode()));

        Map<Object, Object> entries = redisTemplate.opsForHash().entries(RedisConstant.LIKE_NUMBER_INVITATION);


        for (Invitation v: list
             ) {
            Integer sum = (Integer) entries.get(v.getId());

            v.setLikeNum(sum);
        }

        invitationService.updateBatchById(list);

        return SUCCESS;
    }
}
