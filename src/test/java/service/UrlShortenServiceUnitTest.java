package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.learn.SpringBootWorkAroundBranch.entity.ShortenUrl;
import org.learn.SpringBootWorkAroundBranch.hashing.Adler32UrlHashImpl;
import org.learn.SpringBootWorkAroundBranch.repository.UrlRepository;
import org.learn.SpringBootWorkAroundBranch.service.UrlShortenService;
import org.learn.SpringBootWorkAroundBranch.service.UrlShortenServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UrlShortenServiceUnitTest {

    @InjectMocks
    @Qualifier("UrlShortenServiceImpl")
    private UrlShortenServiceImpl urlShortenService;

    @Mock
    private Adler32UrlHashImpl urlHashing;

    @Mock
    private UrlRepository urlRepository;

    @Test
    void shouldGenerateShortenUrlId() {
        String url = "https://www.amazon.com";
        String expectedshorturlid = "2110e410";
        when(urlHashing.generateHashing(any())).thenReturn(expectedshorturlid);
        String actual = urlShortenService.generateShortenUrl(url);
        Assertions.assertEquals(expectedshorturlid, actual);
    }

    @Test
    void shouldGetUrlByShortURL() {
        String url = "https://www.amazon.com";
        String shortUrlId = "2110e410";
        ShortenUrl shortenUrl = new ShortenUrl(shortUrlId, url,new Date(), new Date());
        when(urlRepository.existsById(shortUrlId)).thenReturn(true);
        when(urlRepository.getById(shortUrlId)).thenReturn(shortenUrl);
        ShortenUrl actual = urlShortenService.getUrlByShortURL(shortUrlId);
        Assertions.assertEquals(shortUrlId, actual.getShortURL());
    }

}
