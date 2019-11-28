package com.hsource.controller;


import com.hsource.service.TbUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xwzhou
 * @since 2019-11-27
 */
@RestController
@RequestMapping("/tbUser")
@Api(value = "TbUserController", tags = "用户表")
public class TbUserController {

    @Autowired
    private TbUserService userService;

    @GetMapping("/check/{data}/{type}")
    @ApiOperation(value = "检验数据")
    public ResponseEntity<Boolean> checkData(
            @ApiParam(value = "检验数据", required = true) @PathVariable("data") String data, @ApiParam(value = "检验数据类型",
            required = true)@PathVariable("type") Integer type
    ) {
        return ResponseEntity.ok(userService.checkData(data, type));
    }

}

