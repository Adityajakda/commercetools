package com.anonymousCustomer.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ApiExceptionHandler {

    private ApiException apiException;
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException ex){

        ApiException apiException = new ApiException(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EmptyInputException.class})
    public ResponseEntity<String>handelEmptyImput(EmptyInputException exception){
        return ResponseEntity.ok("empty input");
    }
}
