package com.example.demo.payload.request;

import java.util.Set;

public class SignUpForm {
    private String name;
    private String userName;
    private String password;
    private Set<String> roles;

    public SignUpForm(String name, String userName, String password, Set<String> roles) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public SignUpForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
