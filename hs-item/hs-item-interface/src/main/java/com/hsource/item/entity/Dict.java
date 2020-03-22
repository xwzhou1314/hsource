package com.hsource.item.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hsource.common.mybatiesplus.BaseEntity;
import lombok.Data;

/**
 * <p>
 * 数据字典实体
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-02
 */
@Data
@TableName("dict")
public class Dict extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 数据字典 code
     */
    @TableField("code")
    private String code;
    /**
     * 父节点 code
     */
    @TableField("parent_code")
    private String parentCode;
    /**
     * 数据字典 key
     */
    @TableField("key")
    private String key;
    /**
     * 数据字典 value
     */
    @TableField("value")
    private String value;

}
