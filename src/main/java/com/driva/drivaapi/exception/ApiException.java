package com.driva.drivaapi.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class ApiException {

    private List<String> errors;

    public ApiException(List<String> errors) {
        this.errors = errors;
    }

    public ApiException(String error) {
        this.errors = Collections.singletonList(error);
    }

}
