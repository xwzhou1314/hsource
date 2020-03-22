package com.hsource.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hsource.item.entity.Saying;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xwzhou
 * @since 2020-03-07
 */
public interface SayingService extends IService<Saying> {

    List<Saying> selectList();
}
