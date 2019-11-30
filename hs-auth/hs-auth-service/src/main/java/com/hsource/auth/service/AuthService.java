package com.hsource.auth.service;

import com.hsource.auth.entity.UserInfo;

public interface AuthService {
    String login(String username, String password);

}
