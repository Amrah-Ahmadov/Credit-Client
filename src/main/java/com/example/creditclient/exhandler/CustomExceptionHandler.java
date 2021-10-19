//package com.example.creditclient.exhandler;
//
//import com.example.creditclient.exception.*;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.ConstraintViolationException;
//import java.io.IOException;
//import java.util.Date;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@RestControllerAdvice
//public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(CreditNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<ExceptionClass> handleCreditNotFoundException(CreditNotFoundException creditNotFoundException){
//        ExceptionClass ex = new ExceptionClass(creditNotFoundException.getMessage(), "404");
//        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
//    }
//    @ExceptionHandler(LoanNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<ExceptionClass> handleLoanNotFoundException(LoanNotFoundException loanNotFoundException){
//        ExceptionClass ex = new ExceptionClass(loanNotFoundException.getMessage(), "404");
//        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
//    }
//    @ExceptionHandler(ReclameNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<ExceptionClass> handleReclameNotFoundException(ReclameNotFoundException reclameNotFoundException){
//        ExceptionClass ex = new ExceptionClass(reclameNotFoundException.getMessage(), "404");
//        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
//    }
//    @ExceptionHandler(RepeatPasswordIncorrectException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<ExceptionClass> handleRepeatPasswordIncorrectException(RepeatPasswordIncorrectException repeatPasswordIncorrectException){
//        ExceptionClass ex = new ExceptionClass(repeatPasswordIncorrectException.getMessage(), "400");
//        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
//    }
//    @ExceptionHandler(UserNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<ExceptionClass> handleUserNotFoundException(UserNotFoundException userNotFoundException){
//        ExceptionClass ex = new ExceptionClass(userNotFoundException.getMessage(), "404");
//        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                                  HttpHeaders headers,
//                                                                  HttpStatus status,
//                                                                  WebRequest request) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", new Date());
//        body.put("status", status.value());
//
//        //Get all errors
//        List<String> errors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(x -> x.getDefaultMessage())
//                .collect(Collectors.toList());
//
//        body.put("errors", errors);
//
//        return new ResponseEntity<>(body, headers, status);
//    }
//    @ExceptionHandler(ConstraintViolationException.class)
//    public void constraintViolationException(HttpServletResponse response) throws IOException {
//        response.sendError(HttpStatus.BAD_REQUEST.value());
//    }
//}
