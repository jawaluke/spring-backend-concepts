package org.learn.SpringBootWorkAroundBranch.config;

import org.learn.SpringBootWorkAroundBranch.filter.CustomSecFilter;
import org.learn.SpringBootWorkAroundBranch.security.KeyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
@Configuration
public class WebSecurityConfig {

    @Autowired
    private KeyAuthenticationProvider keyAuthenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChains(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(new CustomSecFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests((request) -> {
            request
                    .anyRequest().permitAll();
        });
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(keyAuthenticationProvider);
    }

}
