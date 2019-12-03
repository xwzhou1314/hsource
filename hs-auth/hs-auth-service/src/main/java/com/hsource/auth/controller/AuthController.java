package com.hsource.auth.controller;


import com.hsource.auth.config.JwtProperties;
import com.hsource.auth.entity.UserInfo;
import com.hsource.auth.service.AuthService;
import com.hsource.auth.utils.JwtUtils;
import com.hsource.common.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties jwtProperties;



    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestParam(value = "username", required = false) String username,
                                      @RequestParam(value = "password", required = false) String password,
                                      HttpServletResponse response, HttpServletRequest request){
        // 登录
        String token = authService.login(username, password);
        // 写入 cookie
        CookieUtils.newBuilder(response).httpOnly().request(request)
                .build(jwtProperties.getCookieName(), token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("verify")
    public ResponseEntity<UserInfo> verify(@CookieValue("HS_TOKEN") String token,
                                           HttpServletResponse response, HttpServletRequest request){
        try {
            UserInfo userInfo = JwtUtils.getUserInfo(jwtProperties.getPublicKey(), token);
            if(null == userInfo){

            }
            // 刷新 cookie
            String newToken = JwtUtils.generateToken(userInfo, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            // 写入 cookie
            CookieUtils.newBuilder(response).httpOnly().request(request)
                    .build(jwtProperties.getCookieName(), newToken);
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            // todo
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
