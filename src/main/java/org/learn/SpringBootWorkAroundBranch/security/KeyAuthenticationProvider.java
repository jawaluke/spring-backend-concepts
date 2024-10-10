package org.learn.SpringBootWorkAroundBranch.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class KeyAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String pass = authentication.getPrincipal().toString();
        if(pass.contains("user") || pass.contains("admin")) {
            String role = pass.equals("user")? "USER": "ADMIN";
            List<SimpleGrantedAuthority> grants = new ArrayList<>(
                    Collections.singleton(new SimpleGrantedAuthority(role))
            );
            return new KeyAuthentication(grants, true, pass);
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(KeyAuthentication.class);
    }
}
