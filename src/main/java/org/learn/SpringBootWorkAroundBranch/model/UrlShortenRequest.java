package org.learn.SpringBootWorkAroundBranch.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlShortenRequest {

    int no;

    @NotNull
    @NotBlank
    String url;
}
