package com.hsource.gateway.config;


import com.hsource.auth.utils.RsaUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PublicKey;

/**
 * 读取 Jwt 配置文件
 * @author xwzhou
 * @date 2019-11-30
 */
@Data
@ConfigurationProperties(prefix = "hs.jwt")
public class JwtProperties {

    /**
     * 公钥地址
     */
    private String pubKeyPath;


    /**
     * cookieName
     */
    private String cookieName;

    /**
     * 公钥
     */
    private PublicKey publicKey;

    /**
     * 对象一旦实例化后，就读取公钥和私钥
     */
    @PostConstruct
    public void init() throws Exception {
        // 读取公钥私钥
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
    }
}
