package com.example.site.user.service;

import com.example.site.user.entity.User;

public interface AuthService {
    User authenticate(String email, String password);
}
