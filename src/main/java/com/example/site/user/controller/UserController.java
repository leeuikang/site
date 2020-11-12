package com.example.site.user.controller;

import com.example.site.user.dto.UserDto;
import com.example.site.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> registerUserInfo(@RequestBody @Valid UserDto.RegisterUserInfo registerUserInfo) {
        userService.registerUserInfo(registerUserInfo);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/users")
    public void deleteById(Authentication authentication) {
        userService.deleteById(authentication);
    }


}
