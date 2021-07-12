package com.driva.drivaapi.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class ExceptionMessage {

    private final HttpStatus httpStatus;
    private final String message;
    private final Integer code;

    public ExceptionMessage(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = httpStatus.value();
    }
}
