package org.learn.SpringBootWorkAroundBranch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlShortenResponse {
    String shortenURLId;
    String shortenURL;
    String originalURL;
    String createdAt;
    String expireAt;
}
