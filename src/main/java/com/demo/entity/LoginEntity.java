package com.demo.entity;

public class LoginEntity {

    public String username;
    public String password;
    public int userId;


    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getuserId() {
        return userId;
    }

    public void setuserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "{userName=" + username
                + ", password=" + password
                + ", userId=" + userId + "}";
    }
}
