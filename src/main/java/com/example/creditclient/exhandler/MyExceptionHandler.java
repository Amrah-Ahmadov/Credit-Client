package com.example.creditclient.exhandler;

import com.example.creditclient.exception.ErrorResponse;
import com.example.creditclient.exception.MyRestTemplateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = MyRestTemplateException.class)
    ResponseEntity<ErrorResponse> handleMyRestTemplateException(MyRestTemplateException ex, HttpServletRequest request) {
        System.out.println("Bura geldi");
        return new ResponseEntity<>(new ErrorResponse(ex), ex.getStatusCode());
    }
}
