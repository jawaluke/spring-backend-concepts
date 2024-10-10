package org.learn.SpringBootWorkAroundBranch.security;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Data
public class KeyAuthentication implements Authentication {

    private String pass;
    private Boolean isAuthenticated;
    @Setter
    private List<SimpleGrantedAuthority> grants;

    public KeyAuthentication(List<SimpleGrantedAuthority> grants, Boolean isAuthenticated, String pass) {
        this.grants = grants;
        this.isAuthenticated = isAuthenticated;
        this.pass = pass;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grants;
    }

    @Override
    public Object getCredentials() {
        return pass;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return pass;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return pass;
    }
}
