package com.example.site.user.service;

import com.example.site.error.ErrorCode;
import com.example.site.error.exception.BusinessException;
import com.example.site.user.entity.User;
import com.example.site.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public User authenticate(String email, String password) {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new BusinessException(ErrorCode.LOGIN_DATA_INVALID_EMAIL);
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BusinessException(ErrorCode.LOGIN_DATA_INVALID_PASSWORD);
        }

        return user;

    }
}
