package org.learn.SpringBootWorkAroundBranch.service;

import org.learn.SpringBootWorkAroundBranch.entity.ShortenUrl;

public interface UrlShortenService {

//    1. algorithm to create a shorten URL from original Url
//    2. save shorten URL to DB along with original URL , created date, expire date
//    3. get original URL from database by shorten URL
//    4. check the whether the url is expired or not.

    String generateShortenUrl(String originalURL);
    ShortenUrl saveShortenURL(ShortenUrl shortenUrl);
    ShortenUrl getUrlByShortURL(String shortenUrl);
    void deleteUrl(String shortURL);
}
