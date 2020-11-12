package com.example.site.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {

    @Getter
    @NoArgsConstructor
    public static class RegisterUserInfo {

        @Email
        private String email;

        @NotBlank
        private String password;

        @NotBlank
        private String userName;
    }
}