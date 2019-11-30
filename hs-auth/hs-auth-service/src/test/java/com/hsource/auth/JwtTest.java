package com.hsource.auth;

import com.hsource.auth.entity.UserInfo;
import com.hsource.auth.utils.JwtUtils;
import com.hsource.auth.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.PrivateKey;
import java.security.PublicKey;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {

    private static final String pubKeyPath = "F:\\rsa\\rsa.pub";

    private static final String priKeyPath = "F:\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo("20", "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU3NTA0NDE2NX0.bZgjhNhU8dZS8V3G6I9ohEXwTO8FBl_kyPLcOnywMkKtEY-b-z0ocx_WVhmS0UN03Xy6uq0A6NVZmu4nHifJIUy1eUhFrBpVUi0ADs652PqzHjj7sHJ-3WBnl5huIv16DXewBMhj7p_Dmy4Le2845LvkX6nAhVN_yTmPmTBQxfs";

        // 解析token
        UserInfo user = JwtUtils.getUserInfo(publicKey, token);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getName());
    }
}