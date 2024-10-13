package org.learn.SpringBootWorkAroundBranch.facade;

import org.learn.SpringBootWorkAroundBranch.model.UrlShortenResponse;

public interface UrlShortenFacade {
    UrlShortenResponse generateShortenUrl(String originalUrl);
}
