package com.hsource.item.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsource.item.dto.saying.SayingDTO;
import com.hsource.item.dto.saying.SayingPageDTO;
import com.hsource.item.entity.Saying;
import com.hsource.item.service.SayingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "根据id获取语录")
    @GetMapping("/selectSayingById")
    public ResponseEntity<Saying> selectSayingById(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok(sayingService.selectSayingById(id));
    }



    @ApiOperation(value = "新增OR修改语录")
    @PostMapping("/insertOrUpdateSaying")
    public ResponseEntity<Void> insertOrUpdateSaying(@ApiParam(value = "数据") @RequestBody SayingDTO dto) {
        sayingService.insertOrUpdateSaying(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "获取语录")
    @PostMapping("/selectPageList")
    public ResponseEntity<Page<Saying>> selectPageList(@ApiParam(value = "数据") @RequestBody SayingPageDTO dto) {
        return ResponseEntity.ok(sayingService.selectPageList(dto));
    }

    @PostMapping("/deleteById")
    @ApiOperation(value = "删除")
    public ResponseEntity<Void> deleteById(@ApiParam(value = "数据") @RequestBody SayingPageDTO dto) {
        return ResponseEntity.ok(sayingService.deleteById(dto));

    }

    @ApiOperation(value = "获取语录")
    @PostMapping("/selectList")
    public ResponseEntity<List<Saying>> selectList() {
        return ResponseEntity.ok(sayingService.selectList());
    }
}

