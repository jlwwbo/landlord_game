package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.UserUtil;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint(value = "/login/{userId}/{password}")
public class WebSocket {
    // 当前在线连接数
    private int onlineCount = 0;

    // 线程安全Set，用来存放每个客户端对应的Session对象。
    private CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<>();

    // session指向用户的map表
    private HashMap<Session, User> sessionToUser = new HashMap<>();

    // 用户指向session的map表
    private HashMap<User, Session> userToSession = new HashMap<>();

    /*
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("userId") String userName,
                       @PathParam("password") String password, Session session) {
        if(!UserUtil.isValidLogin(userName,password)) {
            // 返回失败消息 写在service里面 @do
            return;
        }
        int userId = UserUtil.getUserId(userName);
        User user = new User(session, userId);
        sessions.add(session);
        sessionToUser.put(session, user);
        userToSession.put(user, session);
        onlineCount++;
        System.out.println("有新连接加入！当前在线人数为" + onlineCount);


        // User 登录之后的service @do


    }

    /*
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        User user = sessionToUser.get(session);
        sessions.remove(session);
        sessionToUser.remove(session, user);
        userToSession.remove(user, session);
        onlineCount--;
        System.out.println("有一连接关闭！当前在线人数为" + onlineCount);
    }

    /*
     * 收到客户端消息后调用的方法
     * 确定消息的类型，分给不同的service
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {

        // @do

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