package com.hsource.api;

import com.hsource.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserApi {
    @GetMapping("/query")
    UserDTO queryUsernameAndPassword(@RequestParam("username") String username, @RequestParam("password") String password);
}
