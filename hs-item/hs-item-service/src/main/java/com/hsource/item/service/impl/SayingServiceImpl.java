package com.hsource.item.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsource.common.enums.DelFlagEnum;
import com.hsource.item.entity.Saying;
import com.hsource.item.mapper.SayingMapper;
import com.hsource.item.service.SayingService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xwzhou
 * @since 2020-03-07
 */
@Service
public class SayingServiceImpl extends ServiceImpl<SayingMapper, Saying> implements SayingService {

    @Override
    public List<Saying> selectList() {
        List<Saying> sayings = this.selectList(new EntityWrapper<Saying>()
                .eq("del_falg", DelFlagEnum.DEL_FLAG_FALSE.getCode())
                .orderBy("sort")
                .last("LIMIT 5"));
        return sayings;
    }
}
