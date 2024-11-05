package org.learn.SpringBootWorkAroundBranch.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.learn.SpringBootWorkAroundBranch.security.CusAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CustomSecFilter extends OncePerRequestFilter {

    private AuthenticationManager authenticationManager;

    public CustomSecFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Auth");
        if(authHeader!=null && authHeader.startsWith("Bearer ")) {
            Authentication auth = authenticationManager.authenticate(
                     CusAuthentication.builder()
                            .token(authHeader.substring(7))
                            .isAuthenticated(false)
                            .build()
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
}
