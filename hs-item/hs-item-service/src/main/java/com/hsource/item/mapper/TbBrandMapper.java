package com.hsource.item.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsource.item.entity.TbBrand;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 Mapper 接口
 * </p>
 *
 * @author xwzhou
 * @since 2019-10-24
 */
@Mapper
@Repository
public interface TbBrandMapper extends BaseMapper<TbBrand> {

}
