package com.hsource.item.controller;


import com.hsource.item.dto.reply.InsertReplyDTO;
import com.hsource.item.entity.Invitation;
import com.hsource.item.service.InvitationService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  帖子前端控制器
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-02
 */
@ApiModel(value = "帖子")
@RestController
@RequestMapping("/invitation")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;


    @ApiOperation(value = "获取全部帖子")
    @PostMapping("/selectList")
    public ResponseEntity<List<Invitation>> selectList(){
        return ResponseEntity.ok(invitationService.selectList());
    }

    /**
     * 根据id查询帖子
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询帖子")
    @GetMapping("selectInvitationById")
    public ResponseEntity<Invitation> queryCategoryByIds(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok(invitationService.selectInvitationById(id));
    }

    @ApiOperation(value = "回复互动")
    @PostMapping("replyUser")
    public ResponseEntity<Void> replyUser(@ApiParam(value = "回复互动数据")@Valid @RequestBody InsertReplyDTO dto){
        invitationService.replyUser(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

