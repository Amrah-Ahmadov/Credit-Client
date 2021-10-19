package com.example.creditclient.controller;

import com.example.creditclient.dto.CreditDto;
import com.example.creditclient.dto.CreditPostDto;
import com.example.creditclient.dto.UserDto;
import com.example.creditclient.dto.UserRegisterDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user-client")
public class UserController {
    private static final String webUrl = "http://localhost:8080/user";
    private final RestTemplate restTemplate;

    public UserController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);
        this.restTemplate.setMessageConverters(messageConverters);
    }
    @PostMapping("/register")
    public ResponseEntity<UserDto> insertUser(@RequestBody UserRegisterDto registration){
        HttpEntity<UserRegisterDto> request = new HttpEntity<>(registration);
        ResponseEntity<UserDto> response = restTemplate.exchange(webUrl + "/register", HttpMethod.POST, request, UserDto.class);
        UserDto responseBody = response.getBody();
        return ResponseEntity.ok(responseBody);
    }
}
