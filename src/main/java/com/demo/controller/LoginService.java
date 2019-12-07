package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;

import static com.demo.controller.WebSocket.sendMessage;

@Service
public class LoginService {
    public String userName;
    public String pwd;
    public int userId;
    public Session session;
    public boolean userExist = false;
    public boolean rightPassword = false;

    @Autowired
    public LoginMapper loginMapper;

    public LoginService() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public LoginMapper getLoginMapper() {
        return loginMapper;
    }

    public boolean login() {
        boolean userExist = loginMapper.userExist(userName);
        if(!userExist) {
            rightPassword = false;
            return false;
        }
        boolean rightPassword = loginMapper.rightPassword(userName, pwd);
        if(!rightPassword) {
            return false;
        }
        userId = loginMapper.loginGetUserId(userName,pwd);
        return true;
    }

    public void feedback() throws IOException {
        if(rightPassword) {
            successLogin();
        } else {
            failLogin();
        }
    }

    /*
     * 用来生成成功登录消息的util
     */
    private void successLogin() throws IOException {
        JSONObject message = new JSONObject();
        message.put("type","login");
        message.put("success", true);
        message.put("userName", userName);
        message.put("userId", userId);
        sendMessage(message.toString(), session);
    }
    /*
     * 用来生成失败登录消息的util
     */
    private void failLogin() throws IOException {
        JSONObject message = new JSONObject();
        message.put("type","login");
        message.put("success", false);
        message.put("userName", userName);
        message.put("userExist", userExist);
        sendMessage(message.toString(), session);
    }

}
