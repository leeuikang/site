package com.example.site.user.service;

import com.example.site.user.dto.UserDto;
import org.springframework.security.core.Authentication;

public interface UserService {
    public void registerUserInfo(UserDto.RegisterUserInfo registerUserInfo);
    public void deleteById(Authentication authentication);

}
