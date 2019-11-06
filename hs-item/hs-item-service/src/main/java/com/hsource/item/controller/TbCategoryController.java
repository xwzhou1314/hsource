package com.hsource.item.controller;


import com.hsource.item.entity.TbCategory;
import com.hsource.item.service.TbCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系 前端控制器
 * </p>
 *
 * @author xwzhou
 * @since 2019-10-24
 */

@RestController
@RequestMapping("category")
public class TbCategoryController {


    @Autowired
    private TbCategoryService tbCategoryService;

    /**
     * 根据父类ID查询分类结果
     * @param pid
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<TbCategory>> queryCategoryByPid(@RequestParam(value = "pid",defaultValue = "0") String pid) {
        List<TbCategory> categoryList = tbCategoryService.queryCategoryByPid(pid);
        return ResponseEntity.ok(categoryList);
    }


}

