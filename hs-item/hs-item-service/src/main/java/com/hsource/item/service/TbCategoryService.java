package com.hsource.item.service;

import com.baomidou.mybatisplus.service.IService;
import com.hsource.item.entity.TbCategory;

import java.util.List;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系 服务类
 * </p>
 *
 * @author xwzhou
 * @since 2019-10-24
 */
public interface TbCategoryService extends IService<TbCategory> {

    /**
     * 根据父节点查询到子节点
     *
     * @param pid
     * @return
     */
    List<TbCategory> queryCategoryByPid(String pid);

    /**
     * 根据id查询商品分类
     * @param ids
     * @return
     */
    List<TbCategory> queryCategoryByIds(List<String> ids);
}
