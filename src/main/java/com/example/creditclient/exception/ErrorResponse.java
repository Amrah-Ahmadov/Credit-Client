package com.example.creditclient.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorResponse {
    /** HTTP Status Code */
    private int status;

    /** HTTP Reason phrase */
    private String error;

    /** A message that describe the error thrown when calling the downstream API */
    private String message;

    public ErrorResponse(MyRestTemplateException ex) {
        this.status = ex.getStatusCode().value();
        this.error = ex.getStatusCode().getReasonPhrase();
        this.message = ex.getError();
//                .substring(12, 32);
    }
}
