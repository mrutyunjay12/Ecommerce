package com.Mrutyunjay.ProductService.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class ExceptionDtos {
    private HttpStatus httpStatus;
    private String message;
    public ExceptionDtos(HttpStatus httpStatus,String message)
    {
        this.httpStatus=httpStatus;
        this.message=message;

    }

}
