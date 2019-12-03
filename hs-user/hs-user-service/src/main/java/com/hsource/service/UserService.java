package com.hsource.service;

import com.baomidou.mybatisplus.service.IService;
import com.hsource.dto.UserDTO;
import com.hsource.entry.User;

/**
 * <p>
 *  用户服务类
 * </p>
 *
 * @author xwzhou
 * @since 2019-12-02
 */
public interface UserService extends IService<User> {
    /**
     * 检验数据
     *
     * @param data
     * @param type
     * @return
     */
    Boolean checkData(String data, Integer type);

    /**
     * 注册
     *
     * @param userDTO
     * @param code
     */
    void register(UserDTO userDTO, String code);

    /**
     * 根据用户名密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    UserDTO queryUsernameAndPassword(String username, String password);
}
