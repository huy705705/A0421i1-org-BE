package com.example.demo.service.impl;

import com.example.demo.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private Collection<? extends  GrantedAuthority> roles;
//    List<GrantedAuthority> authorities = null;

//    public AccountDetailsImpl(Long id, String username, String password,
//                              List<GrantedAuthority> authorities  ) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.authorities = authorities;
//
//    }


    public AccountDetailsImpl() {
    }

    public AccountDetailsImpl(Long id, String username, String password, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    // This func help you to get account information to AccountDetailService
    public static AccountDetailsImpl build (Account account) {
        List<GrantedAuthority> authorities = account.getRoleSet().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().getRoleName()))
                .collect(Collectors.toList());
        return new AccountDetailsImpl(
                account.getAccountId(),
                account.getAccountName(),
                account.getPassword(),
                authorities

        );
    }

    public void setUsername(String username) {
        this.username = username;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
