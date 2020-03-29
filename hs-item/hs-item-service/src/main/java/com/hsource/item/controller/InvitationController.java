package com.hsource.item.controller;


import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsource.item.dto.invitation.InvitationDTO;
import com.hsource.item.dto.invitation.InvitationPageDTO;
import com.hsource.item.dto.invitation.InvitationSearchDTO;
import com.hsource.item.dto.reply.InsertReplyDTO;
import com.hsource.item.entity.Invitation;
import com.hsource.item.service.InvitationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  帖子前端控制器
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-02
 */
@Api(tags = "帖子")
@RestController
@RequestMapping("/invitation")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @ApiOperation(value = "新增OR修改")
    @PostMapping("/insertOrUpdate")
    public ResponseEntity<Void> insertOrUpdate(@ApiParam(value = "数据") @RequestBody InvitationDTO dto) {
        invitationService.insertOrUpdate(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/searchListPage")
    @ApiOperation(value = "帖子列表")
    public ResponseEntity<Page<Invitation>> searchListPage(@ApiParam(value = "数据")@RequestBody InvitationPageDTO dto) {
        return ResponseEntity.ok(invitationService.searchListPage(dto));

    }

    @PostMapping("/deleteById")
    @ApiOperation(value = "删除")
    public ResponseEntity<Void> deleteById(@ApiParam(value = "数据")@RequestBody InvitationPageDTO dto) {
        return ResponseEntity.ok(invitationService.deleteById(dto));

    }

    @ApiOperation(value = "获取全部帖子")
    @PostMapping("/selectList")
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @Cached(expire = 60, cacheType = CacheType.REMOTE)
    @CacheRefresh(refresh = 300, stopRefreshAfterLastAccess = 3600, timeUnit = TimeUnit.SECONDS)
    public ResponseEntity<List<Invitation>> selectList(@ApiParam(value = "回复互动数据")@Valid @RequestBody InvitationSearchDTO dto){
        return ResponseEntity.ok(invitationService.selectListByDto(dto));
    }

    @ApiOperation(value = "获取部分帖子")
    @PostMapping("/selectSixList")
    public ResponseEntity<List<Invitation>> selectSixList(){
        return ResponseEntity.ok(invitationService.selectSixList());
    }

    @ApiOperation(value = "获取热门帖子")
    @PostMapping("/selectListHot")
    public ResponseEntity<List<Invitation>> selectListHot(){
        return ResponseEntity.ok(invitationService.selectListHot());
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

    @ApiOperation(value = "点赞")
    @PostMapping("likeNum")
    public ResponseEntity<Void> likeNum(@RequestParam(value = "id") String id){
        invitationService.likeNum(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

