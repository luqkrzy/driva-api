package com.driva.drivaapi.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
   
   @ExceptionHandler(value = UserRoleNotFoundException.class)
   @ResponseBody
   @ResponseStatus(HttpStatus.NOT_FOUND)
   protected ExceptionMessage handleUserRoleNotFoundException(UserRoleNotFoundException exception) {
      return new ExceptionMessage(HttpStatus.NOT_FOUND, exception.getMessage());
   }
   
   @ExceptionHandler(value = StudentAlreadyExistException.class)
   @ResponseBody
   @ResponseStatus(HttpStatus.CONFLICT)
   protected ExceptionMessage handleStudentAlreadyExistException(StudentAlreadyExistException exception) {
      return new ExceptionMessage(HttpStatus.CONFLICT, exception.getMessage());
   }
   
   @ExceptionHandler(value = DateAlreadyExistException.class)
   @ResponseBody
   @ResponseStatus(HttpStatus.CONFLICT)
   protected ExceptionMessage handleDateAlreadyExistException(DateAlreadyExistException exception) {
      return new ExceptionMessage(HttpStatus.CONFLICT, exception.getMessage());
   }
   
   @ExceptionHandler(value = EmailAlreadyExist.class)
   @ResponseBody
   @ResponseStatus(HttpStatus.CONFLICT)
   protected ExceptionMessage handleEmailAlreadyExistException(EmailAlreadyExist exception) {
      return new ExceptionMessage(HttpStatus.CONFLICT, exception.getMessage());
   }
   
   @ExceptionHandler(value = UserRoleExistsException.class)
   @ResponseBody
   @ResponseStatus(HttpStatus.CONFLICT)
   protected ExceptionMessage handleUserRoleExistException(UserRoleExistsException exception) {
      return new ExceptionMessage(HttpStatus.CONFLICT, exception.getMessage());
   }
   
   @ExceptionHandler(value = ConstraintViolationException.class)
   @ResponseBody
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   protected ExceptionMessage handleConstraintViolationException(ConstraintViolationException exception) {
      return new ExceptionMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
   }
   
   @ExceptionHandler(value = DataIntegrityViolationException.class)
   @ResponseBody
   @ResponseStatus(HttpStatus.CONFLICT)
   protected ExceptionMessage handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
      return new ExceptionMessage(HttpStatus.CONFLICT, exception.getMessage());
   }
   
   @ExceptionHandler(value = ProductTypeNotFoundException.class)
   @ResponseBody
   @ResponseStatus(HttpStatus.NOT_FOUND)
   protected ExceptionMessage handleUserRoleNotFoundException(ProductTypeNotFoundException exception) {
      return new ExceptionMessage(HttpStatus.NOT_FOUND, exception.getMessage());
   }
   
   @ExceptionHandler(value = WorkDayNotFoundException.class)
   @ResponseBody
   @ResponseStatus(HttpStatus.NOT_FOUND)
   protected ExceptionMessage handleWorkDayNotFoundException(WorkDayNotFoundException exception) {
      return new ExceptionMessage(HttpStatus.NOT_FOUND, exception.getMessage());
   }
   
   @ExceptionHandler(value = InstructorNotFoundException.class)
   @ResponseBody
   @ResponseStatus(HttpStatus.NOT_FOUND)
   protected ExceptionMessage handleInstructorFoundException(InstructorNotFoundException exception) {
      return new ExceptionMessage(HttpStatus.NOT_FOUND, exception.getMessage());
   }
   
   @ExceptionHandler(value = ProductNotFoundException.class)
   @ResponseBody
   @ResponseStatus(HttpStatus.NOT_FOUND)
   protected ExceptionMessage handleProductNotFoundException(ProductNotFoundException exception) {
      return new ExceptionMessage(HttpStatus.NOT_FOUND, exception.getMessage());
   }
   
   @ExceptionHandler(value = LessonNotFoundException.class)
   @ResponseBody
   @ResponseStatus(HttpStatus.NOT_FOUND)
   protected ExceptionMessage handleLessonNotFoundException(LessonNotFoundException exception) {
      return new ExceptionMessage(HttpStatus.NOT_FOUND, exception.getMessage());
   }
   
   @ExceptionHandler(value = StudentNotFoundException.class)
   @ResponseBody
   @ResponseStatus(HttpStatus.NOT_FOUND)
   protected ExceptionMessage handleUserRoleNotFoundException(StudentNotFoundException exception) {
      return new ExceptionMessage(HttpStatus.NOT_FOUND, exception.getMessage());
    }
    
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ExceptionMessage handleUserRoleNotFoundException(AccessDeniedException exception) {
       return new ExceptionMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }
   
   @ExceptionHandler(value = UsernameNotFoundException.class)
   @ResponseBody
   @ResponseStatus(HttpStatus.NOT_FOUND)
   public ExceptionMessage handleUsernameNotFoundException(final UsernameNotFoundException exception) {
      return new ExceptionMessage(HttpStatus.NOT_FOUND, exception.getMessage());
   }
   
   @NonNull
   protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull final MethodArgumentNotValidException ex,
                                                                 @NonNull final HttpHeaders headers,
                                                                 @NonNull final HttpStatus status,
                                                                 @NonNull final WebRequest request) {
      logger.info(ex.getClass().getName());
      final List<String> errors = new ArrayList<>();
      final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
      
      fieldErrors.forEach(fieldError -> errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage()));
      final ApiException apiException = new ApiException(errors, HttpStatus.BAD_REQUEST);
      return handleExceptionInternal(ex, apiException, headers, HttpStatus.BAD_REQUEST, request);
   }
}
