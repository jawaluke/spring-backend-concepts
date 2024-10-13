package org.learn.SpringBootWorkAroundBranch.facade;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.learn.SpringBootWorkAroundBranch.entity.ShortenUrl;
import org.learn.SpringBootWorkAroundBranch.model.UrlShortenResponse;
import org.learn.SpringBootWorkAroundBranch.service.ExpiryDateService;
import org.learn.SpringBootWorkAroundBranch.service.UrlShortenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UrlShortenFacadeImpl implements UrlShortenFacade {

    private UrlShortenService urlShortenService;
    private ExpiryDateService expiryDateService;
    private HttpServletRequest httpServletRequest;

    @Autowired
    public UrlShortenFacadeImpl(ExpiryDateService expiryDateService, HttpServletRequest httpServletRequest
            , UrlShortenService urlShortenService) {
        this.expiryDateService = expiryDateService;
        this.httpServletRequest = httpServletRequest;
        this.urlShortenService = urlShortenService;
    }

    @Override
    @Transactional
    public UrlShortenResponse generateShortenUrl(@NotNull @NotBlank String originalUrl) {
        UrlShortenResponse urlShortenResponse = null;
        String shortenUrl = urlShortenService.generateShortenUrl(originalUrl);
        Date createdAt = new Date();
        Date expireAt = expiryDateService.calculateExpiryDate(createdAt, 2);
        ShortenUrl shortenUrlEntity = new ShortenUrl(shortenUrl,
                originalUrl,
                createdAt,
                expireAt
        );
        ShortenUrl savedShortenURL = urlShortenService.saveShortenURL(shortenUrlEntity);
        if (savedShortenURL != null) {
            urlShortenResponse = UrlShortenResponse.builder()
                    .shortenURLId(savedShortenURL.getShortURL())
                    .shortenURL(generateRedirectionURL(savedShortenURL.getShortURL()))
                    .originalURL(savedShortenURL.getOriginalURL())
                    .createdAt(savedShortenURL.getCreatedTime().toString())
                    .expireAt(savedShortenURL.getExpireTime().toString())
                    .build();
        }
        return urlShortenResponse;
    }

    private String generateRedirectionURL(String shortenUrlId) {
        String scheme = httpServletRequest.getScheme();
        String serverName = httpServletRequest.getServerName();
        int serverPort = httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String baseUrl = scheme + "://" + serverName + ":" + serverPort + contextPath
                + "/api/base" + "/redirect" + "/" + shortenUrlId;
        return baseUrl;
    }
}
