package com.abiha.springboot.bootcampproject.exception;

import com.abiha.springboot.bootcampproject.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
@RestController
@Slf4j
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex , WebRequest request){

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        log.error("Exception occured",ex);
        return new ResponseEntity(exceptionResponse , HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex , WebRequest request){

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse , HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object> handleBadRequestException(BadRequestException ex , WebRequest request){

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Object> handleProductVariationNotFoundException(EntityNotFoundException ex , WebRequest request){

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse , HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ValidationFailedException.class)
    public final ResponseEntity<Object> handleValidationFailedException(ValidationFailedException ex , WebRequest request){

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductOutOfStockException.class)
    public final ResponseEntity<Object> handleProductOutOfStockException(ProductOutOfStockException ex , WebRequest request){

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse , HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(CartIsEmptyException.class)
    public final ResponseEntity<Object> handleCartIsEmptyException(CartIsEmptyException ex , WebRequest request){

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceForbiddenException.class)
    public final ResponseEntity<Object> handleResourceForbiddenException(ResourceForbiddenException ex , WebRequest request){

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse , HttpStatus.FORBIDDEN);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,HttpStatus status , WebRequest request){

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed", ex.getBindingResult().getAllErrors().toString());

        return new ResponseEntity(exceptionResponse , HttpStatus.BAD_REQUEST);

    }


}