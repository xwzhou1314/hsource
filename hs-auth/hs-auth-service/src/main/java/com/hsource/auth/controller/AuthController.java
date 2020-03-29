package com.hsource.auth.controller;


import com.hsource.auth.config.JwtProperties;
import com.hsource.auth.entity.UserInfo;
import com.hsource.auth.service.AuthService;
import com.hsource.auth.utils.JwtUtils;
import com.hsource.common.utils.CookieUtils;
import com.hsource.common.utils.ImageUtil;
import com.hsource.common.utils.NumberUtils;
import com.hsource.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties jwtProperties;

    @RequestMapping("/createImg")
    public void createImg(HttpServletResponse response){
        try {
            //设置相应类型,告诉浏览器输出的内容为图片
            response.setContentType("image/jpeg");
            //设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            BufferedImage bufferedImage = ImageUtil.generateVerifyCodeImage(NumberUtils.createCode());
            ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
        }catch (Exception e){

        }
    }


    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestBody UserDTO dto,
                                      HttpServletResponse response, HttpServletRequest request){
        // 登录
        String token = authService.login(dto.getUsername(), dto.getPassword());
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
