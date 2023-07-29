package com.luv2code.springboot.demoSecurity.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class WebUser {

    @NotNull(message="is required")
    @Size(min = 1, message="is required")
    private String userName;

    @NotNull(message="is required")
    @Size(min = 1, message="is required")
    private String password;

    public String getUserName() {
        return userName;
    }

    public WebUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
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
}
