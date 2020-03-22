package com.hsource.item.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hsource.item.entity.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  数据字典 Mapper 接口
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-02
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict> {

}
