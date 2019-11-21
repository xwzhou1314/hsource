package com.hsource.item.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsource.item.entity.TbCategory;
import com.hsource.item.mapper.TbCategoryMapper;
import com.hsource.item.service.TbCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系 服务实现类
 * </p>
 *
 * @author xwzhou
 * @since 2019-10-24
 */
@Service
public class TbCategoryServiceImpl extends ServiceImpl<TbCategoryMapper, TbCategory> implements TbCategoryService {

    @Override
    public List<TbCategory> queryCategoryByPid(String pid) {
        EntityWrapper<TbCategory> wrapper = new EntityWrapper<>();
        wrapper.eq("parent_id", pid);
        return this.selectList(wrapper);
    }

    @Override
    public List<TbCategory> queryCategoryByIds(List<String> ids) {
        return this.selectBatchIds(ids);
    }
}
