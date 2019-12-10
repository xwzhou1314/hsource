package com.hsource.item.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsource.common.enums.ExceptionEnum;
import com.hsource.common.exception.HsException;
import com.hsource.item.entity.Dict;
import com.hsource.item.mapper.DictMapper;
import com.hsource.item.service.DictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  数据字典服务实现类
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-02
 */
@Service
@Transactional
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    /**
     * 根据 字典 code 查询 数据字典
     *
     * @param code
     * @return
     */
    @Override
    public Dict selectDictByCode(String code) {
        Dict dict = this.selectOne(new EntityWrapper<Dict>().eq("code", code));
        HsException hs = new HsException(ExceptionEnum.DICT_NE_NULL);
        if(null == dict){
            throw new HsException(ExceptionEnum.DICT_NE_NULL);
        }
        return dict;
    }

    /**
     * 根据 字典 父节点code 查询 数据字典
     *
     * @param code
     * @return List<Dict>
     */
    @Override
    public List<Dict> selectDictByParentCode(String code) {
        return this.selectList(new EntityWrapper<Dict>().eq("parent_code", code));
    }

    /**
     * 根据 字典 key 查询 数据字典
     *
     * @param code 父节点
     * @return map<code, value>
     */
    @Override
    public Map<String, String> selectDictByKey(String code) {
        return this.selectList(new EntityWrapper<Dict>().eq("parent_code", code))
                .stream().collect(Collectors.toMap(Dict::getCode, Dict::getValue));
    }


}
