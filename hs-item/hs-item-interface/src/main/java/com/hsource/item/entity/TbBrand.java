package com.hsource.item.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hsource.common.mybatiesplus.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系
 * </p>
 *
 * @author xwzhou
 * @since 2019-10-24
 */
@TableName("tb_brand")
@Data
public class TbBrand extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 品牌名称
     */
    @TableField("name")
    private String name;
    /**
     * 品牌图片地址
     */
    @TableField("image")
    private String image;
    /**
     * 品牌的首字母
     */
    @TableField("letter")
    private String letter;

}
