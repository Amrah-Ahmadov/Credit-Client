package com.example.creditclient.controller;

import com.example.creditclient.dto.CreditDto;
import com.example.creditclient.dto.CreditPostDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/credit-client")
public class CreditController {
    private static final String webUrl = "http://localhost:8080/credit";
    private final RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(CreditController.class);

    public CreditController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);
        this.restTemplate.setMessageConverters(messageConverters);
    }
    @PostMapping
    public ResponseEntity<CreditDto> addNewCredit(HttpServletRequest httpServletRequest,
                                                  @RequestBody CreditPostDto credit){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        headers.setBearerAuth(token);
        HttpEntity<CreditPostDto> request = new HttpEntity<>(credit,headers);
        ResponseEntity<CreditDto> response = restTemplate.exchange(webUrl, HttpMethod.POST, request, CreditDto.class);
        CreditDto responseBody = response.getBody();
        return ResponseEntity.ok(responseBody);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<CreditDto> deleteCreditById(HttpServletRequest httpServletRequest,
                                                      @PathVariable Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        headers.setBearerAuth(token);
        HttpEntity<Long> request = new HttpEntity<>(id, headers);
        ResponseEntity<CreditDto> response = restTemplate.exchange(webUrl +"/" + id, HttpMethod.DELETE,request, CreditDto.class);
        CreditDto responseBody = response.getBody();
        return ResponseEntity.ok(responseBody);
    }
    @GetMapping("{id}")
    public ResponseEntity<CreditDto> getCreditById(HttpServletRequest httpServletRequest,
                                                   @PathVariable Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        headers.setBearerAuth(token);
        HttpEntity<Long> request = new HttpEntity<>(id, headers);
        try{
            ResponseEntity<CreditDto> result = restTemplate.exchange(webUrl +"/" + id, HttpMethod.GET,request, CreditDto.class);
            CreditDto responseBody = result.getBody();
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (RestClientException e){
            System.out.println("AAAAAAAAAAAA   " + e.getMessage());
            e.printStackTrace();
            if(e instanceof HttpClientErrorException.NotFound){
                System.out.println("BBBBBBBB");
//                throw new CreditNotFoundException();
            }else{
                System.out.println("An error occurred while trying to parse Login Response JSON object");
            }
        }
        return null;
    }
    @GetMapping
    public ResponseEntity<List<CreditDto>> getAllCredits(HttpServletRequest httpServletRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        headers.setBearerAuth(token);
        HttpEntity<List> entity = new HttpEntity<>(headers);
        ResponseEntity<List> result = restTemplate.exchange(webUrl,HttpMethod.GET,entity, List.class);
        List<CreditDto> responseBody = result.getBody();
        return ResponseEntity.ok(responseBody);
    }
}
