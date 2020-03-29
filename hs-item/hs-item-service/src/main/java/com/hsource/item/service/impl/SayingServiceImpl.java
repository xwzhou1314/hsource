package com.hsource.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsource.common.enums.DelFlagEnum;
import com.hsource.common.enums.ExceptionEnum;
import com.hsource.common.exception.HsException;
import com.hsource.common.utils.UuidUtil;
import com.hsource.item.dto.saying.SayingDTO;
import com.hsource.item.dto.saying.SayingPageDTO;
import com.hsource.item.entity.Saying;
import com.hsource.item.mapper.SayingMapper;
import com.hsource.item.service.SayingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        List<Saying> sayings = this.list(new QueryWrapper<Saying>()
                .eq("del_falg", DelFlagEnum.DEL_FLAG_FALSE.getCode())
                .orderByAsc("sort")
                .last("LIMIT 5"));
        return sayings;
    }

    @Override
    public Page<Saying> selectPageList(SayingPageDTO dto) {
        QueryWrapper<Saying> wrapper = new QueryWrapper<>();
        if(null != dto && StringUtils.isNotBlank(dto.getContent())){
            wrapper.like("content", dto.getContent());
        }
        Page<Saying> page = this.page(new Page<>(dto.getPage(), dto.getPageSize()), wrapper);
        return page;
    }

    @Override
    public Void deleteById(SayingPageDTO dto) {
        Saying byId = this.getOne(new QueryWrapper<Saying>()
                .eq("del_falg", DelFlagEnum.DEL_FLAG_FALSE.getCode())
                .eq("id", dto.getId()));
        if(null == byId){
            throw new HsException(ExceptionEnum.DEL_USER_NULL);
        }
        byId.setDelFalg(DelFlagEnum.DEL_FLAG_TRUE.getCode());
        this.saveOrUpdate(byId);
        return null;
    }

    /**
     * 新增OR修改语录
     *
     * @param dto
     */
    @Override
    public void insertOrUpdateSaying(SayingDTO dto) {
        Saying saying = new Saying();
        BeanUtils.copyProperties(dto, saying);
        if(StringUtils.isNotBlank(dto.getId())){
            saying.setDelFalg(DelFlagEnum.DEL_FLAG_TRUE.getCode());
        }else {
            saying.setDelFalg(DelFlagEnum.DEL_FLAG_FALSE.getCode());
            saying.setId(UuidUtil.get32UUID());
            saying.setCreateDate(new Date());
        }

        this.saveOrUpdate(saying);
    }

    @Override
    public Saying selectSayingById(String id) {
        return this.getById(id);
    }
}
