package com.hsource.common.mybatiesplus;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 基础model
 * @author: xwzhou
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseEntity extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 唯一id
     */
    @TableId
    private String id;
    /**
     * 记录操作人
     */
    private String operateUser;
    /**
     * 记录操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateDate;

    /**
     * 记录操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**
     * 备注
     */
    private String remark;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
