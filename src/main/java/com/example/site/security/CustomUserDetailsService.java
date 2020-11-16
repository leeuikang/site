package com.example.site.security;

import com.example.site.user.entity.User;
import com.example.site.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findById(Integer.parseInt(id));

        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException(id + " 사용자를 찾을 수 없습니다.");
        }

        User user = userOptional.get();

        return new org.springframework.security.core.userdetails
                .User(String.valueOf(user.getId()), user.getPassword(), Collections.emptyList());
    }
}
