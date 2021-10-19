package com.example.creditclient.controller;
import com.example.creditclient.dto.ReclameDto;
import com.example.creditclient.dto.ReclamePostDto;
import com.example.creditclient.exhandler.RestTemplateResponseErrorHandler;
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
@RequestMapping("/reclame-client")
public class ReclameController {
    private static final String webUrl = "http://localhost:8080/reclame";
    private final RestTemplate restTemplate;

    public ReclameController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

        this.restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);
        this.restTemplate.setMessageConverters(messageConverters);
    }

    @PostMapping
    public ResponseEntity<ReclameDto> addNewReclame(HttpServletRequest httpServletRequest,
                                                    @RequestBody ReclamePostDto reclame){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        headers.setBearerAuth(token);
        HttpEntity<ReclamePostDto> request = new HttpEntity<>(reclame,headers);
        ResponseEntity<ReclameDto> response = restTemplate.exchange(webUrl, HttpMethod.POST, request, ReclameDto.class);
        ReclameDto responseBody = response.getBody();
        return ResponseEntity.ok(responseBody);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<ReclameDto> deleteReclameById(HttpServletRequest httpServletRequest,
                                                        @PathVariable Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        headers.setBearerAuth(token);
        HttpEntity<Long> request = new HttpEntity<>(id, headers);
        ResponseEntity<ReclameDto> response = restTemplate.exchange(webUrl +"/" + id, HttpMethod.DELETE,request, ReclameDto.class);
        ReclameDto responseBody = response.getBody();
        return ResponseEntity.ok(responseBody);
    }
    @GetMapping("{id}")
    public ResponseEntity<ReclameDto> getReclameById(HttpServletRequest httpServletRequest,
                                                     @PathVariable Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        headers.setBearerAuth(token);
        HttpEntity<Long> request = new HttpEntity<>(id, headers);
        ResponseEntity<ReclameDto> result = restTemplate.exchange(webUrl +"/" + id, HttpMethod.GET,request, ReclameDto.class);
        ReclameDto responseBody = result.getBody();
        return ResponseEntity.ok(responseBody);
    }
    @GetMapping
    public ResponseEntity<List<ReclameDto>> getAllReclames(HttpServletRequest httpServletRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        headers.setBearerAuth(token);
        HttpEntity<List> entity = new HttpEntity<>(headers);
        ResponseEntity<List> result = restTemplate.exchange(webUrl,HttpMethod.GET,entity, List.class);
        List<ReclameDto> responseBody = result.getBody();
        return ResponseEntity.ok(responseBody);
    }
}
