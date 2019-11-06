package com.hsource.item.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsource.item.entity.TbCategoryBrand;
import com.hsource.item.mapper.TbCategoryBrandMapper;
import com.hsource.item.service.TbCategoryBrandService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品分类和品牌的中间表，两者是多对多关系 服务实现类
 * </p>
 *
 * @author xwzhou
 * @since 2019-10-28
 */
@Service
public class TbCategoryBrandServiceImpl extends ServiceImpl<TbCategoryBrandMapper, TbCategoryBrand> implements TbCategoryBrandService {

}
