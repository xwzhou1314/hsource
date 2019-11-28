package com.hsource.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsource.entry.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author xwzhou
 * @since 2019-11-27
 */
@Mapper
@Repository
public interface TbUserMapper extends BaseMapper<TbUser> {

}
