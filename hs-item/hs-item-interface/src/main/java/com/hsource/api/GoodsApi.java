package com.hsource.api;

import com.hsource.item.entity.TbCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GoodsApi {
    @GetMapping("category/list/ids")
    List<TbCategory> queryCategoryByIds(@RequestParam(value = "ids") List<String> ids);
}
