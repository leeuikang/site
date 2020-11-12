package com.example.site.user.service;

import com.example.site.user.dto.UserDto;
import org.springframework.security.core.Authentication;

import javax.security.auth.login.LoginException;

public interface UserService {
    public void registerUserInfo(UserDto.RegisterUserInfo registerUserInfo) throws LoginException;
    public void deleteById(Authentication authentication);

}
