package com.hsource.item.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hsource.common.mybatiesplus.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xwzhou
 * @since 2020-03-07
 */
@Data
@TableName("saying")
public class Saying extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**
     * 语录
     */
    @TableField("content")
    private String content;


    /**
     * 语录
     */
    @TableField("sort")
    private long sort;

    /**
     * 颜色
     */
    @TableField("color")
    private String color;

    /**
     * 是否删除 0保留 1 删除
     */
    @TableField("del_falg")
    private long delFalg;
}
