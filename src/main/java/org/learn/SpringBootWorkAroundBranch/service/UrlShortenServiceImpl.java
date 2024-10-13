package org.learn.SpringBootWorkAroundBranch.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.learn.SpringBootWorkAroundBranch.entity.ShortenUrl;
import org.learn.SpringBootWorkAroundBranch.hashing.UrlHashingAlgo;
import org.learn.SpringBootWorkAroundBranch.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UrlShortenServiceImpl implements UrlShortenService {

    private final UrlRepository urlRepository;

    private final UrlHashingAlgo urlHashingAlgo;

    @Autowired
    public UrlShortenServiceImpl(@Qualifier("adler32hash") UrlHashingAlgo urlHashingAlgo, UrlRepository urlRepository) {
        this.urlHashingAlgo = urlHashingAlgo;
        this.urlRepository = urlRepository;
    }

    @Override
    @Transactional
    public String generateShortenUrl(@NotEmpty String originalURL) {
        String urlWithDate = originalURL.concat(String.valueOf(LocalDateTime.now()));
        return urlHashingAlgo.generateHashing(urlWithDate);
    }

    @Override
    @Transactional
    public ShortenUrl saveShortenURL(ShortenUrl shortenUrl) {
        return urlRepository.save(shortenUrl);
    }

    @Override
    @Transactional
    public ShortenUrl getUrlByShortURL(@NotNull @NotBlank String shortenUrl) {
        if(urlRepository.existsById(shortenUrl)) {
            return urlRepository.getById(shortenUrl);
        }
        return null;
    }

    @Override
    public void deleteUrl(String shortURL) {
         urlRepository.deleteById(shortURL);
    }
}
