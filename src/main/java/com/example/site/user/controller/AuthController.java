package com.example.site.user.controller;

import com.example.site.security.JwtTokenProvider;
import com.example.site.user.entity.User;
import com.example.site.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/token")
public class AuthController {
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("")
    public String obtainToken(@RequestBody Map<String, String> cred){

        User user = authService.authenticate(cred.get("email"),cred.get("password"));

        return jwtTokenProvider.createToken(String.valueOf(user.getId()));
    }
}
