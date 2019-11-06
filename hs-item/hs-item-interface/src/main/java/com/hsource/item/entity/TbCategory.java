package com.hsource.item.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hsource.common.mybatiesplus.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系
 * </p>
 *
 * @author xwzhou
 * @since 2019-10-24
 */
@TableName("tb_category")
@Data
public class TbCategory extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 类目名称
     */
    @TableField("name")
    private String name;
    /**
     * 父类目id,顶级类目填0
     */
    @TableField("parent_id")
    private Long parentId;
    /**
     * 是否为父节点，0为否，1为是
     */
    @TableField("is_parent")
    private Integer isParent;
    /**
     * 排序指数，越小越靠前
     */
    @TableField("sort")
    private Integer sort;

}
