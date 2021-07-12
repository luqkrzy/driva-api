package com.driva.drivaapi.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class ApiException {

    private final List<String> errors;
    private final HttpStatus httpStatus;
    private final Integer code;

    public ApiException(List<String> errors, HttpStatus httpStatus) {
        this.errors = errors;
        this.httpStatus = httpStatus;
        this.code = httpStatus.value();
    }

    public ApiException(String error, HttpStatus httpStatus) {
        this.errors = Collections.singletonList(error);
        this.httpStatus = httpStatus;
        this.code = httpStatus.value();
    }

}
