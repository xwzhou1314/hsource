package com.hsource.item.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.hsource.item.dto.TBCategoryQureyDTO;
import com.hsource.item.entity.TbBrand;
import com.hsource.item.service.TbBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 前端控制器
 * </p>
 *
 * @author xwzhou
 * @since 2019-10-24
 */
@RestController
@RequestMapping("brand")
public class TbBrandController {

    @Autowired
    private TbBrandService tbBrandService;

    /**
     * 分页查询品牌信息
     *
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<Page<TbBrand>> queryBrandByPage(@RequestParam(value = "key", required = false) String key, HttpServletResponse httpServletResponse) {
        TBCategoryQureyDTO dto = new TBCategoryQureyDTO();
        dto.setKey(key);
        return ResponseEntity.ok(tbBrandService.queryBrandByPage(dto));
    }

    /**
     * 新增品牌接口
     *
     * @param brand
     * @param cids
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> saveBrand(TbBrand brand, @RequestParam("cids") List<String> cid) {
        tbBrandService.saveBrand(brand, cid);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

