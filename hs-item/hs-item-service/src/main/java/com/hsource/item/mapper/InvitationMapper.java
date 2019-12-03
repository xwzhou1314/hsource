package com.hsource.item.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsource.item.entity.Invitation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  帖子 Mapper 接口
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-02
 */
@Mapper
@Repository
public interface InvitationMapper extends BaseMapper<Invitation> {

}
