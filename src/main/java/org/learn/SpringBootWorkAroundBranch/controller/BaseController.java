package org.learn.SpringBootWorkAroundBranch.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.learn.SpringBootWorkAroundBranch.entity.ShortenUrl;
import org.learn.SpringBootWorkAroundBranch.facade.UrlShortenFacade;
import org.learn.SpringBootWorkAroundBranch.model.UrlShortenRequest;
import org.learn.SpringBootWorkAroundBranch.model.UrlShortenResponse;
import org.learn.SpringBootWorkAroundBranch.service.ExpiryDateService;
import org.learn.SpringBootWorkAroundBranch.service.UrlShortenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@RequestMapping("/api/base")
@RestController
@Slf4j
public class BaseController {

    private final UrlShortenService urlShortenService;

    private final UrlShortenFacade urlShortenFacade;

    private final ExpiryDateService expiryDateService;

    @Autowired
    public BaseController(UrlShortenService urlShortenService, UrlShortenFacade urlShortenFacade
            , ExpiryDateService expiryDateService) {
        this.urlShortenService = urlShortenService;
        this.urlShortenFacade = urlShortenFacade;
        this.expiryDateService = expiryDateService;
    }

    @PostMapping("/url")
    public ResponseEntity<?> getInfo(@RequestBody UrlShortenRequest urlShortenRequest) {
        log.info("Request: {}",urlShortenRequest.toString());
        if(urlShortenRequest.getUrl() == null) {
            return ResponseEntity.badRequest().body("no url found");
        }
        UrlShortenResponse urlShortenResponse = urlShortenFacade.generateShortenUrl(urlShortenRequest.getUrl());
        Assert.notNull(urlShortenResponse, "something went wrong while hashing");
        return ResponseEntity.ok(urlShortenResponse);
    }

    @GetMapping("/redirect/{redirectURL}")
    public ResponseEntity<?> redirect(@NotBlank @PathVariable String redirectURL, HttpServletResponse httpServletResponse) throws IOException {
        ShortenUrl shortenUrl = urlShortenService.getUrlByShortURL(redirectURL);
        if(shortenUrl == null) {
            return ResponseEntity.notFound().build();
        }
        if (expiryDateService.checkDateExpired(shortenUrl.getExpireTime())) {
            urlShortenService.deleteUrl(shortenUrl.getShortURL());
            return ResponseEntity.badRequest().body("Requested Url is expired");
        }
        httpServletResponse.sendRedirect(shortenUrl.getOriginalURL());
        return null;
    }

}
