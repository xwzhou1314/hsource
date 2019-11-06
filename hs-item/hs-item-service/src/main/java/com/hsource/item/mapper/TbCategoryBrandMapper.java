package com.hsource.item.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsource.item.entity.TbCategoryBrand;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商品分类和品牌的中间表，两者是多对多关系 Mapper 接口
 * </p>
 *
 * @author xwzhou
 * @since 2019-10-28
 */
@Mapper
@Repository
public interface TbCategoryBrandMapper extends BaseMapper<TbCategoryBrand> {

}
