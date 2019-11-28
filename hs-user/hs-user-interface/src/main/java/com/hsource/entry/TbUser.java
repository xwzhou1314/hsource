package com.hsource.entry;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hsource.common.mybatiesplus.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author xwzhou
 * @since 2019-11-27
 */
@TableName("tb_user")
@Data
public class TbUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;
    /**
     * 密码，加密存储
     */
    @TableField("password")
    private String password;
    /**
     * 注册手机号
     */
    @TableField("phone")
    private String phone;
    /**
     * 创建时间
     */
    @TableField("created")
    private Date created;
}
