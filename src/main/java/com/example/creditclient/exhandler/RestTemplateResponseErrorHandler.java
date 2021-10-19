package com.example.creditclient.exhandler;

import com.example.creditclient.exception.MyCustomException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (
                httpResponse.getStatusCode().series() == CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {

        if (httpResponse.getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR) {
            System.out.println("HHHHHHHHHHHHHHHHHHH");
            // handle SERVER_ERROR
        } else if (httpResponse.getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR) {
            // handle CLIENT_ERROR
            System.out.println("11111111111111111111");
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
//                throw new NotFoundException();                                     ///burada
//                throw new MyCustomException(httpResponse.getStatusCode(), httpResponse.getBody().toString(), httpResponse.getBody().toString());
//                String body = IOUtils.toString(httpResponse.getBody());
                System.out.println("222222222222222222222");
                MyCustomException exception = new MyCustomException(httpResponse.getStatusCode(),httpResponse.getBody().toString(), httpResponse.getBody().toString());
                throw exception;
            }
        }
    }
}
