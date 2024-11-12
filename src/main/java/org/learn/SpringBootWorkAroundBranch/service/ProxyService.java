package org.learn.SpringBootWorkAroundBranch.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Enumeration;

@Service
public class ProxyService {

    private final RestTemplate restTemplate;

    @Autowired
    public ProxyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity getFromInternet(HttpServletRequest httpServletRequest) {
        String url = httpServletRequest.getRequestURL().toString();
        HttpMethod requestMethod = HttpMethod.valueOf(httpServletRequest.getMethod());
        HttpHeaders httpHeaders = new HttpHeaders();
        Enumeration<String> headers = httpServletRequest.getHeaderNames();

        while(headers.hasMoreElements()) {
            String names = headers.nextElement();
            httpHeaders.add(names, httpServletRequest.getHeader(names));
        }

        httpHeaders.add("Accept", "*/*");
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        System.out.println("httpEntity = " + httpEntity + " url :"+ url + " method: "+requestMethod);
        ResponseEntity response = null;

        response = fromInternet(url, requestMethod, httpEntity);
        while(response.getStatusCode().is3xxRedirection()) {
            String newLocation = response.getHeaders().getLocation().toString();
            response = fromInternet(newLocation, requestMethod, httpEntity);
            System.out.println("response.getHeaders().toString() = " + response.getHeaders().toString());
            System.out.println("response = " + response.getStatusCode());
        }

        System.out.println("response.getHeaders().toString() = " + response.getHeaders().toString());
        System.out.println("response = " + response.getStatusCode());
        return new ResponseEntity<>(response.getBody(), response.getHeaders(), response.getStatusCode());
    }

    public ResponseEntity fromInternet(String url, HttpMethod requestMethod, HttpEntity httpEntity) {
        return restTemplate.exchange(url, requestMethod, httpEntity, byte[].class);
    }

}
