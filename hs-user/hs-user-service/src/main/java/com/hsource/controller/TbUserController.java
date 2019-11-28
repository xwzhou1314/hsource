package com.hsource.controller;


import com.hsource.dto.UserDTO;
import com.hsource.service.TbUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xwzhou
 * @since 2019-11-27
 */
@RestController
@Api(value = "TbUserController", tags = "用户")
@RequestMapping("user")
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

    @PostMapping("register")
    @ApiOperation(value = "检验数据")
    public ResponseEntity<Void> register(
            @ApiParam(value = "用户数据", required = true)@Valid UserDTO userDTO, @ApiParam(value = "验证码",
            required = true)String code
    ) {
        userService.register(userDTO, code);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/query")
    @ApiOperation(value = "根据用户名密码查询用户")
    public ResponseEntity<UserDTO> queryUsernameAndPassword(
            @ApiParam(value = "用户名", required = true) @RequestParam("username") String username, @ApiParam(value = "密码",
            required = true)@PathVariable("password") String password
    ) {
        return ResponseEntity.ok(userService.queryUsernameAndPassword(username, password));
    }



}

