package com.hsource.mybatiesplus.live_register.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 现场注册 
 * </p>
 *
 * @author xwzhou
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LiveRegister implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private Long modifier;

    /**
     * 更新时间
     */
    @TableField("modifyTime")
    private LocalDateTime modifyTime;

    /**
     * 现场id
     */
    @TableField("liveId")
    private Long liveId;

    /**
     * 用户id
     */
    @TableField("userId")
    private Long userId;

    /**
     * 状态
     */
    private Integer status;


}
