package com.anonymousCustomer.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
public class ApiException {
    private final String message;
//    private final Throwable throwable;
    private final HttpStatus httpStatus;

}
