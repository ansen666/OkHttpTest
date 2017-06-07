package com.ansen.entity;

/**
 * Created by  ansen
 * Create Time 2017-06-05
 */
public class User extends BaseResult{
    private String  username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
