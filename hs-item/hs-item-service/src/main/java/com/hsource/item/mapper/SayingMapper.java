package com.hsource.item.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsource.item.entity.Saying;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xwzhou
 * @since 2020-03-07
 */
@Mapper
@Repository
public interface SayingMapper extends BaseMapper<Saying> {

}
