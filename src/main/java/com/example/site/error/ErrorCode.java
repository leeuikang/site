package com.example.site.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    //common
    POST_IS_NOT_EXIST(400, "post is not exist"),
    //member
    EMAIL_DUPLICATION(400,"email is duplication"),
    LOGIN_DATA_INVALID(400, "login data is invalid");

    private int status;
    private String message;

    ErrorCode(final int status, final String message){
        this.status = status;
        this.message = message;
    }

}
