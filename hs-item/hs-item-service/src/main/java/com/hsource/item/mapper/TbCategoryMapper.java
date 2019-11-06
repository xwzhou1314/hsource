package com.hsource.item.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsource.item.entity.TbCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系 Mapper 接口
 * </p>
 *
 * @author binchen5
 * @since 2019-10-24
 */
@Mapper
@Repository
public interface TbCategoryMapper extends BaseMapper<TbCategory> {

    List<TbCategory> idList();
}
