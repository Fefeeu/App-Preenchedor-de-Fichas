package com.rpp.api.domain.user;

public enum UserRole {
    BASIC("basic"),
    PREMIUM("premium");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
