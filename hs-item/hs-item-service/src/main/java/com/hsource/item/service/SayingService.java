package com.hsource.item.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsource.item.dto.saying.SayingDTO;
import com.hsource.item.dto.saying.SayingPageDTO;
import com.hsource.item.entity.Saying;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xwzhou
 * @since 2020-03-07
 */
public interface SayingService extends IService<Saying> {

    List<Saying> selectList();

    Page<Saying> selectPageList(SayingPageDTO dto);

    Void deleteById(SayingPageDTO dto);

    /**
     * 新增OR修改语录
     *
     * @param dto
     */
    void insertOrUpdateSaying(SayingDTO dto);

    Saying selectSayingById(String id);
}
