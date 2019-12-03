package com.hsource.api;

import com.hsource.dto.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户Api
 * @author xwzhou
 * @date 2019-12-02
 */
public interface UserApi {
    /**
     * 根据用户名密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/user/query")
    UserDTO queryUsernameAndPassword(@RequestParam(value = "username", required = false) String username,
                                     @RequestParam(value = "password", required = false) String password);
}
