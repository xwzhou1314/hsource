package com.hsource.item.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hsource.common.mybatiesplus.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 商品分类和品牌的中间表，两者是多对多关系
 * </p>
 *
 * @author xwzhou
 * @since 2019-10-28
 */
@TableName("tb_category_brand")
@Data
public class TbCategoryBrand extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品类目id
     */
    @TableId("category_id")
    private String categoryId;
    /**
     * 品牌id
     */
    @TableField("brand_id")
    private String brandId;

}
