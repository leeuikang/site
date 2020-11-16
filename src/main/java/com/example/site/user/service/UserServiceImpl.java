package com.example.site.user.service;

import com.example.site.error.ErrorCode;
import com.example.site.error.exception.BusinessException;
import com.example.site.user.dto.UserDto;
import com.example.site.user.entity.User;
import com.example.site.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUserInfo(UserDto.RegisterUserInfo registerUserInfo){
        Optional<User> userExisted = userRepository.findByEmail(registerUserInfo.getEmail());

        if(userExisted.isPresent())
            throw new BusinessException(ErrorCode.EMAIL_DUPLCATION);

        String password = passwordEncoder.encode(registerUserInfo.getPassword());

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(registerUserInfo, User.class);
        user.setPassword(password);

        userRepository.save(user);
    }

    @Override
    public void deleteById(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> user = userRepository.findById(Integer.parseInt(userDetails.getUsername()));

        if(user.isPresent())
            userRepository.deleteById(Integer.parseInt(userDetails.getUsername()));
    }


}
