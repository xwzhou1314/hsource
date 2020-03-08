package com.hsource.controller;


import com.hsource.dto.UserDTO;
import com.hsource.dto.UserPageDTO;
import com.hsource.entry.User;
import com.hsource.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  用户前端控制器
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/check/{data}/{type}")
    @ApiOperation(value = "检验数据")
    public ResponseEntity<Boolean> checkData(
            @ApiParam(value = "检验数据", required = true) @PathVariable("data") String data, @ApiParam(value = "检验数据类型", required = true)@PathVariable("type") Integer type
    ) {
        return ResponseEntity.ok(userService.checkData(data, type));
    }

    @PostMapping("searchList")
    @ApiOperation(value = "用户号列表")
    public ResponseEntity<Page<User>> searchList(@ApiParam(value = "用户数据")@RequestBody UserPageDTO dto) {
        return ResponseEntity.ok(userService.searchList(dto));

    }
    @PostMapping("delUserById")
    @ApiOperation(value = "用户号删除")
    public ResponseEntity<Void> delUserById(@ApiParam(value = "用户数据")@RequestBody UserPageDTO dto) {
        userService.delUserById(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PostMapping("register")
    @ApiOperation(value = "注册")
    public ResponseEntity<Void> register(
            @ApiParam(value = "用户数据")@Valid @RequestBody UserDTO userDTO
    ) {
        userService.register(userDTO, userDTO.getCode());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/query")
    @ApiOperation(value = "根据用户名密码查询用户")
    public ResponseEntity<UserDTO> queryUsernameAndPassword(
            @ApiParam(value = "用户名", required = true) @RequestParam(value = "username", required = false) String username, @ApiParam(value = "密码",
            required = true)@RequestParam(value = "password", required = false) String password
    ) {
        return ResponseEntity.ok(userService.queryUsernameAndPassword(username, password));
    }

    /**
     * 根据id 查找 用户是否存在
     *
     * @param id
     * @return true 不存在
     */
    @PostMapping("/selectUserById")
    @ApiOperation(value = "根据id 查找 用户是否存在")
    public ResponseEntity<Boolean> selectUserById(@ApiParam(value = "用户 id" )@RequestParam(value = "id", required = false) String id){
        return ResponseEntity.ok(userService.selectUserById(id));
    }
}

