package com.hsource.item.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsource.item.entity.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  评论 Mapper 接口
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-08
 */
@Mapper
@Repository
public interface ReplyMapper extends BaseMapper<Reply> {

}
