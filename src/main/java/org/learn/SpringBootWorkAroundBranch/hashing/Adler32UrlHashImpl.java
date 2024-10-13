package org.learn.SpringBootWorkAroundBranch.hashing;

import com.google.common.hash.Hashing;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;

@Component("adler32hash")
public class Adler32UrlHashImpl implements UrlHashingAlgo {

    @Override
    public String generateHashing(@NotNull @NotBlank String url) {
        String hashString = Hashing.adler32().hashString(url, StandardCharsets.UTF_16).toString();
        Assert.notNull(hashString, "Something went wrong while hashing");
        return hashString;
    }

}
