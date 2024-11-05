package org.learn.SpringBootWorkAroundBranch.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Scope(value = "singleton")
@Component
public class JwtUtil {

    private JwtBuilder jwts = Jwts
                .builder().issuer("galaxy");

    private JwtParser jwtParser;

    private String key = "gfdsdfyguhijkhugyftdrsrdtfygu3546576879nbyss";

    private SecretKey secretKey;

    @PostConstruct
    private void sign() {
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
        jwts.
                signWith(secretKey)
                .header()
                .type("JWT")
                .and();
        jwtParser = Jwts.parser().verifyWith(secretKey).build();
    }

    public String generateJwt(String user, String password) {
        return jwts
                .header().add("creator", "malcom").and()
                .claims().issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .add("pass", password)
                .add("user", user)
                .subject("malcom security token")
                .and()
                .compact();
    }

    public String getJwtClient(String principal, String client) {
        return jwtParser.parseSignedClaims(principal)
                .getPayload().get(client).toString();
    }
}
