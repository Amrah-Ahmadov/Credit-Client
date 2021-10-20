package com.example.creditclient.exhandler;
import com.example.creditclient.exception.MyRestTemplateException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateResponseErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (
                httpResponse.getStatusCode().series() == CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()))) {
                String httpBodyResponse = reader.lines().collect(Collectors.joining(","));
                /**
                 * Yuxaridaki httpBodyResponse bele olacaq texmini olaraq - {"message":"No such a Loan found","statusCode":"404"}
                 */
                Optional<String> errorMessage = Arrays.stream(httpBodyResponse.split("[,{}]"))
                        .filter(x -> x != null && !x.isEmpty() && x.startsWith("\"message")).findFirst();
                /**
                 *   "errorMessage texmini bu curdur :      "message":"No such a Loan found"
                 */
                String message = "";    /// bu message errorMessage den message ve ":" ni legv edib tekce No such a Loan found u goturmek ucundur
                if(errorMessage.isPresent()){
                    message =  Arrays.stream(errorMessage.get().split("[:\"]"))
                            .filter(x -> x != null && !x.isEmpty()).collect(Collectors.toList()).get(1);
                }
                throw new MyRestTemplateException(response.getStatusCode(), message);
            }
        }
    }
}
