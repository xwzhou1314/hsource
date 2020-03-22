package com.hsource.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hsource.item.entity.Dict;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字典服务类
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-02
 */
public interface DictService extends IService<Dict> {

    /**
     * 根据 字典 code 查询 数据字典
     *
     * @param code
     * @return
     */
    Dict selectDictByCode(String code);


    /**
     * 根据 字典 父节点code 查询 数据字典
     * @param code 父节点
     * @return list
     */
    List<Dict> selectDictByParentCode(String code);

    /**
     * 根据 字典 key 查询 数据字典
     * @param code 父节点
     * @return map<code, value>
     */
    Map<String, String> selectDictByKey(String code);
}
