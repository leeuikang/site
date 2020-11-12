package com.example.site.user.service;

import com.example.site.user.dto.UserDto;
import com.example.site.user.entity.User;
import com.example.site.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void registerUserInfo(UserDto.RegisterUserInfo registerUserInfo) {

    }

    @Override
    public void deleteById(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> user = userRepository.findById(Integer.parseInt(userDetails.getUsername()));
        if(user.isPresent())
            userRepository.deleteById(Integer.parseInt(userDetails.getUsername()));
    }


}
