package com.example.site.error;

import com.example.site.error.exception.AccessDeniedException;
import com.example.site.error.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e){

        ErrorCode errorCode = e.getErrorCode();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getStatus()).message(errorCode.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
    }

    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e){

        ErrorCode errorCode = e.getErrorCode();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
    }


}
