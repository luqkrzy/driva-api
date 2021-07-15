package com.driva.drivaapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UserRoleNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ExceptionMessage handleUserRoleNotFoundException(UserRoleNotFoundException exception, WebRequest webRequest) {
        return new ExceptionMessage(HttpStatus.NOT_FOUND, exception.getMessage());

    }

    @ExceptionHandler(value = StudentAlreadyExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ExceptionMessage handleUserRoleNotFoundException(StudentAlreadyExistException exception, WebRequest webRequest) {
        return new ExceptionMessage(HttpStatus.CONFLICT, exception.getMessage());

    }

    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull final MethodArgumentNotValidException ex, @NonNull final HttpHeaders headers,
                                                                  @NonNull final HttpStatus status, @NonNull final WebRequest request) {
        logger.info(ex.getClass().getName());
        final List<String> errors = new ArrayList<>();
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        fieldErrors.forEach(fieldError ->
                errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage())
        );
        final ApiException apiException = new ApiException(errors, HttpStatus.BAD_REQUEST);
        return handleExceptionInternal(ex, apiException, headers, HttpStatus.BAD_REQUEST, request);
    }

}
