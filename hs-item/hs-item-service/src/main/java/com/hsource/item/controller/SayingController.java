package com.hsource.item.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsource.item.dto.saying.SayingPageDTO;
import com.hsource.item.entity.Saying;
import com.hsource.item.service.SayingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xwzhou
 * @since 2020-03-07
 */

@Api(tags = "语录")
@RestController
@RequestMapping("/saying")
public class SayingController {

    @Autowired
    private SayingService sayingService;


    @ApiOperation(value = "获取语录")
    @PostMapping("/selectPageList")
    public ResponseEntity<Page<Saying>> selectPageList(@ApiParam(value = "数据") @RequestBody SayingPageDTO dto) {
        return ResponseEntity.ok(sayingService.selectPageList(dto));
    }
    @PostMapping("/deleteById")
    @ApiOperation(value = "删除")
    public ResponseEntity<Void> deleteById(@ApiParam(value = "数据")@RequestBody SayingPageDTO dto) {
        return ResponseEntity.ok(sayingService.deleteById(dto));

    }

    @ApiOperation(value = "获取语录")
    @PostMapping("/selectList")
    public ResponseEntity<List<Saying>> selectList() {
        return ResponseEntity.ok(sayingService.selectList());
    }
}

