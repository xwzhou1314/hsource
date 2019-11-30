package com.hsource.item.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsource.common.enums.ExceptionEnum;
import com.hsource.common.exception.HsException;
import com.hsource.common.utils.UuidUtil;
import com.hsource.item.dto.TBCategoryQureyDTO;
import com.hsource.item.entity.TbBrand;
import com.hsource.item.entity.TbCategoryBrand;
import com.hsource.item.mapper.TbBrandMapper;
import com.hsource.item.service.TbBrandService;
import com.hsource.item.service.TbCategoryBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 服务实现类
 * </p>
 *
 * @author xwzhou
 * @since 2019-10-24
 */
@Service
@Transactional
public class TbBrandServiceImpl extends ServiceImpl<TbBrandMapper, TbBrand> implements TbBrandService {

    @Autowired
    private TbCategoryBrandService categoryBrandService;

    @Override
    public Page<TbBrand> queryBrandByPage(TBCategoryQureyDTO dto) {

        EntityWrapper<TbBrand> wrapper = new EntityWrapper<>();
        wrapper.like("name",dto.getKey())
                .or()
                .like("letter",dto.getKey());
        Page<TbBrand>  page = this.selectPage(new Page<>(1, 20), wrapper);
        return page;
    }

    @Override
    public void saveBrand(TbBrand brand, List<String> cid) {
        brand.setId(UuidUtil.get32UUID());
        boolean isSave = this.insert(brand);
        if(false == isSave){
            throw new HsException(ExceptionEnum.BRAND_SAVE_FAILED);
        }
        // 新增中间表
        for (String id:cid
             ) {
            TbCategoryBrand categoryBrand = new TbCategoryBrand();
            categoryBrand.setBrandId(brand.getId());
            categoryBrand.setCategoryId(id);
            categoryBrandService.insert(categoryBrand);
        }
    }
}
