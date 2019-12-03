package com.hsource.entry;

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
 * 用户
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-02
 */
@Data
@TableName("user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String username;
    /**
     * 邮箱
     */
    @TableField("mail")
    private String mail;
    /**
     * 密码word
     */
    @TableField("password")
    private String password;
    /**
     * 是否删除 0保留 1 删除
     */
    @TableField("del_falg")
    private String delFalg;
    /**
     * 用户类别 0 管理员 1普通用户
     */
    @TableField("user_type")
    private String userType;
    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;
    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;
}
