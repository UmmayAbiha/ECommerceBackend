package com.abiha.springboot.bootcampproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CartIsEmptyException extends RuntimeException{

    public CartIsEmptyException(String message) {
        super(message);
    }
}
