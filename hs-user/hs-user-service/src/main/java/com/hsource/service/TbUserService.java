package com.hsource.service;

import com.baomidou.mybatisplus.service.IService;
import com.hsource.entry.TbUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xwzhou
 * @since 2019-11-27
 */
public interface TbUserService extends IService<TbUser> {

    /**
     * 检验数据
     *
     * @param data
     * @param type
     * @return
     */
    Boolean checkData(String data, Integer type);
}
