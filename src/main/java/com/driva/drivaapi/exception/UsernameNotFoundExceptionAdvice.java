package com.driva.drivaapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UsernameNotFoundExceptionAdvice {

    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionMessage handleUsernameNotFoundException(final UsernameNotFoundException exception) {
        return new ExceptionMessage(HttpStatus.NOT_FOUND, exception.getMessage());
    }
}
