package com.demo.entity;

public class LoginEntity {

    public String userName;
    public String pwd;
    public int userId;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "{userName=" + userName
                + ", password=" + pwd
                + ", userId=" + userId + "}";
    }
}
