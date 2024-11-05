package org.learn.SpringBootWorkAroundBranch.security;

import org.learn.SpringBootWorkAroundBranch.service.UserService;
import org.learn.SpringBootWorkAroundBranch.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Primary
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public CustomAuthenticationProvider(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getPrincipal().toString();
        String username = jwtUtil.getJwtClient(token, "user");
        String password = jwtUtil.getJwtClient(token, "pass");
        Objects.requireNonNull(username, "username not found in token");
        Objects.requireNonNull(password, "password not found in token");
        if(!userService.validateUser(username, password)) {
            return null;
        };
        return CusAuthentication.builder()
                .isAuthenticated(true)
                .token(token)
                .username(username)
                .password(password)
                .build();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CusAuthentication.class);
    }
}
