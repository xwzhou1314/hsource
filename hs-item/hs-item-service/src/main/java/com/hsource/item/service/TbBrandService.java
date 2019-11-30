package com.hsource.item.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hsource.item.dto.TBCategoryQureyDTO;
import com.hsource.item.entity.TbBrand;

import java.util.List;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 服务类
 * </p>
 *
 * @author xwzhou
 * @since 2019-10-24
 */
public interface TbBrandService extends IService<TbBrand> {

    Page<TbBrand> queryBrandByPage(TBCategoryQureyDTO dto);

    void saveBrand(TbBrand brand, List<String> cid);
}
