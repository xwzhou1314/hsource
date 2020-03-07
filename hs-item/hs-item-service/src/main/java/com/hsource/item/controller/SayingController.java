package com.hsource.item.controller;


import com.hsource.item.entity.Saying;
import com.hsource.item.service.SayingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xwzhou
 * @since 2020-03-07
 */
@Controller
@RequestMapping("/saying")
public class SayingController {

    @Autowired
    private SayingService sayingService;



    @ApiOperation(value = "获取语录")
    @PostMapping("/selectList")
    public ResponseEntity<List<Saying>> selectList(){
        return ResponseEntity.ok(sayingService.selectList());
    }
}

