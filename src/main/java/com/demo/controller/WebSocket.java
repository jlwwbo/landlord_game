package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.entity.UserEntity;
import com.demo.service.SearchFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
@ServerEndpoint(value = "/login/{userId}/{password}")
public class WebSocket {
    // 当前在线连接数
    private int onlineCount = 0;

    // 线程安全Set，用来存放每个客户端对应的Session对象。
    private CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<>();

    // session指向用户的map表
    private HashMap<Session, UserEntity> sessionToUserEntity = new HashMap<>();

    // 用户指向session的map表
    private HashMap<Integer, Session> userIdToSession = new HashMap<>();

    public Timestamp timestamp;

    @Autowired
    LoginService loginService;

    
    @OnOpen
    public void onOpen(@PathParam("userId") String userName,
                       @PathParam("password") String password, Session session) throws IOException {
        System.out.println(loginService);
        loginService.setUserName(userName);
        loginService.setPwd(password);
        loginService.setSession(session);
        System.out.println(loginService.getLoginMapper());
        boolean success = loginService.login();
        if(success) {
            loginRegister(session, loginService.getUserId());
        }
        loginService.feedback();
    }

    /*
     * 用于注册含session的成员变量
     */
    private void loginRegister(Session session,int userId) {
        onlineCount++;
        UserEntity userEntity = new UserEntity(userId);
        sessions.add(session);
        sessionToUserEntity.put(session, userEntity);
        userIdToSession.put(userId, session);
        System.out.println("有新连接加入！当前在线人数为" + onlineCount);
    }

    /*
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        onlineCount--;
        UserEntity userEntity = sessionToUserEntity.get(session);
        sessions.remove(session);
        sessionToUserEntity.remove(session, userEntity);
        userIdToSession.remove(userEntity.userID, session);
        System.out.println("有一连接关闭！当前在线人数为" + onlineCount);
    }

    /*
     * 收到客户端消息后调用的方法
     * 确定消息的类型，分给不同的service
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String rawMessage, Session session) throws IOException {
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp.toString() + rawMessage);
        JSONObject message = JSON.parseObject(rawMessage);
        String type = message.getString("type");
        if(type.equals("searchFriend")) {
            String nickName = message.getString("nickName");
            int userId = message.getInteger("userId");
            SearchFriend searchFriend = new SearchFriend(nickName,userId,session);
            searchFriend.search();
            searchFriend.feedback();
        }
    }
    /*
     * @param session
     * @param error
     */
    @OnError
    public void onError(Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    /*
     * 通过session确认给谁发信息
     *
     * @param session
     * @param message
     */
    public static void sendMessage(String message, Session session) throws IOException {
        session.getBasicRemote().sendText(message);
    }


    /*
     * 群发自定义消息
     */
    public void sendInfo(String message) throws IOException {
        for (Session session : sessions) {
            try {
                sendMessage(message, session);
            } catch (IOException ignored) { }
        }
    }

    /*
     * 测试一个session的连接是否正常
     * @return true/false
     */
    public boolean connectionTest(Session session) {
        try {
            sendMessage("", session);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}