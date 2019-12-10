package com.hsource.item.controller;


import com.hsource.item.entity.Dict;
import com.hsource.item.service.DictService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  数据字典前端控制器
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-02
 */
@ApiModel(value = "数据字典")
@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @ApiOperation(value = "根据 字典 code 查询 数据字典")
    @PostMapping("selectDictByCode")
    public ResponseEntity<Dict> selectDictByCode(@ApiParam(value = "数据字典code", required = true) @RequestParam(value = "code") String code){
        return ResponseEntity.ok(dictService.selectDictByCode(code));
    }

    @ApiOperation(value = "字典 父节点code 查询 数据字典")
    @PostMapping("selectDictByParentCode")
    public ResponseEntity<List<Dict>> selectDictByParentCode(@ApiParam(value = "数据字典父code", required = true) @RequestParam(value = "code") String code){
        return ResponseEntity.ok(dictService.selectDictByParentCode(code));
    }

}

