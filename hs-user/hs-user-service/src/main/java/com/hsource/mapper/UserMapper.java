package com.hsource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hsource.entry.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  用户Mapper 接口
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-02
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
