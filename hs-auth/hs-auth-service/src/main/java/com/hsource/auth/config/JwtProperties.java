package com.hsource.auth.config;


import com.hsource.auth.utils.RsaUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
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
     * 登录校验的密钥
     */
    private String secret;

    /**
     * 公钥地址
     */
    private String pubKeyPath;

    /**
     * 私钥地址
     */
    private String priKeyPath;

    /**
     * 过期时间,单位分钟
     */
    private int expire;

    /**
     * 公钥
     */
    private PublicKey publicKey;

    /**
     * 私钥
     */
    private PrivateKey privateKey;

    /**
     * cookieName
     */
    private String cookieName;

    /**
     * 对象一旦实例化后，就读取公钥和私钥
     */
    @PostConstruct
    public void init() throws Exception {
        // 公钥私钥如果不存在
        File pubPath = new File(pubKeyPath);
        File priPath = new File(priKeyPath);

        if(!priPath.exists() || !pubPath.exists()){
            // 生成公钥私钥
            RsaUtils.generateKey(pubKeyPath, priKeyPath, secret);
        }
        // 读取公钥私钥
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }
}
