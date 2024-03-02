package com.Mrutyunjay.ProductService.Exceptions;

import com.Mrutyunjay.ProductService.dtos.ExceptionDtos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDtos> handleNotFoundException(NotFoundException notFoundException)
    {
        return new ResponseEntity<>(new ExceptionDtos(HttpStatus.NOT_FOUND,notFoundException.getMessage()),HttpStatus.NOT_FOUND);
    }
}
