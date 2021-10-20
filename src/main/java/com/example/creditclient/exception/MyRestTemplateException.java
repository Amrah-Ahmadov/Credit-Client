package com.example.creditclient.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
@Getter
@ToString
public class MyRestTemplateException extends RuntimeException{
    private HttpStatus statusCode;
    private String error;

    public MyRestTemplateException(HttpStatus statusCode, String error) {
        super(error);
        this.statusCode = statusCode;
        this.error = error;
    }
}
