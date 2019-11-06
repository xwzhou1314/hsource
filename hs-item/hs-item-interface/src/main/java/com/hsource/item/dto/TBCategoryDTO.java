package com.hsource.item.dto;

import com.hsource.common.utils.SplitPageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xwzhou4
 * @date 2019/10/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TBCategoryDTO extends SplitPageDTO {

    /**
     * 商品skuId
     */
    private Long skuId;
    /**
     * 购买数量
     */
    private Integer num;
    /**
     * 购买数量
     */
    private String key;
}
