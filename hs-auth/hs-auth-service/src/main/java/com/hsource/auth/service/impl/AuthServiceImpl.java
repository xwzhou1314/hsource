package com.hsource.auth.service.impl;

import com.hsource.auth.client.UserClient;
import com.hsource.auth.config.JwtProperties;
import com.hsource.auth.entity.UserInfo;
import com.hsource.auth.service.AuthService;
import com.hsource.auth.utils.JwtUtils;
import com.hsource.common.enums.ExceptionEnum;
import com.hsource.common.exception.HsException;
import com.hsource.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@EnableConfigurationProperties(JwtProperties.class)
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public String login(String username, String password) {

        try {
            // 校验用户名密码
            UserDTO userDTO = userClient.queryUsernameAndPassword(username, password);
            if (null == userDTO || StringUtils.isEmpty(userDTO.getId())) {
                throw new HsException(ExceptionEnum.USER_PASS_FALSE);
            }
            // 生成token
            String token = JwtUtils.generateToken(new UserInfo(userDTO.getId(), username), jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            return token;
        } catch (Exception e) {
            throw new HsException(ExceptionEnum.USER_PASS_FALSE);
        }
    }
}
