package com.hsource.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsource.entry.TbUser;
import com.hsource.mapper.TbUserMapper;
import com.hsource.service.TbUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xwzhou
 * @since 2019-11-27
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

    @Override
    public Boolean checkData(String data, Integer type) {

        EntityWrapper<TbUser> wrapper = new EntityWrapper<>();
        switch (type){
            case 1:
                wrapper.eq("username", data);
                break;
            case 2:
                wrapper.eq("phone", data);
                break;
        }
        return 0 == this.selectCount(wrapper);
    }
}
