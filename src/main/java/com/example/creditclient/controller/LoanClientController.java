package com.example.creditclient.controller;

import com.example.creditclient.dto.LoanDto;
import com.example.creditclient.dto.LoanPostDto;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/loan-client")
public class LoanClientController {
    private static final String webUrl = "http://localhost:8080/loan";
    private final RestTemplate restTemplate;

    public LoanClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);
        this.restTemplate.setMessageConverters(messageConverters);
    }

    @PostMapping
    public ResponseEntity<LoanDto> addNewLoan(HttpServletRequest httpRequest, @RequestBody LoanPostDto loan){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final String token = httpRequest.getHeader("Authorization").substring(7);
        headers.setBearerAuth(token);
        HttpEntity<Object> request = new HttpEntity<>(loan,headers);
        ResponseEntity<LoanDto> response = restTemplate.exchange(webUrl,HttpMethod.POST, request, LoanDto.class);
        LoanDto responseBody = response.getBody();
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping
    public ResponseEntity<List<LoanDto>> getAllLoans(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final String token = request.getHeader("Authorization").substring(7);
        headers.setBearerAuth(token);
        HttpEntity<List> entity = new HttpEntity<List>(headers);
        ResponseEntity<List> result = restTemplate.exchange(webUrl,HttpMethod.GET,entity, List.class);
        List<LoanDto> responseBody = result.getBody();
        return ResponseEntity.ok(responseBody);
    }
    @GetMapping("{id}")
    public ResponseEntity<LoanDto> getLoanById(HttpServletRequest httpRequest, @PathVariable Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final String token = httpRequest.getHeader("Authorization").substring(7);
        headers.setBearerAuth(token);
        HttpEntity<Long> request = new HttpEntity<>(id, headers);
        ResponseEntity<LoanDto> result = restTemplate.exchange(webUrl +"/" + id, HttpMethod.GET,request, LoanDto.class);
        LoanDto responseBody = result.getBody();
        return ResponseEntity.ok(responseBody);
    }
    @PutMapping("{id}")
    public ResponseEntity<LoanDto> updateLoan(HttpServletRequest httpRequest,
                                              @PathVariable Long id,
                                              @RequestBody LoanPostDto loan){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final String token = httpRequest.getHeader("Authorization").substring(7);
        headers.setBearerAuth(token);
        HttpEntity<LoanPostDto> request = new HttpEntity<>(loan, headers);
        ResponseEntity<LoanDto> response = restTemplate.exchange(webUrl +"/" + id, HttpMethod.PUT,request, LoanDto.class);
        LoanDto responseBody = response.getBody();
        return ResponseEntity.ok(responseBody);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<LoanDto> deleteLoanById(HttpServletRequest httpRequest, @PathVariable Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final String token = httpRequest.getHeader("Authorization").substring(7);
        headers.setBearerAuth(token);
        HttpEntity<Long> request = new HttpEntity<Long>(id, headers);
        ResponseEntity<LoanDto> response = restTemplate.exchange(webUrl +"/" + id, HttpMethod.DELETE,request, LoanDto.class);
        LoanDto responseBody = response.getBody();
        return ResponseEntity.ok(responseBody);
    }
}
