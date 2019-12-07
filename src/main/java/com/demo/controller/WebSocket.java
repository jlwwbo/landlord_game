package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.model.user.User;
//import com.demo.model.user.UserUtil;
import com.demo.service.SearchFriend;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint(value = "/login/{userId}/{password}")
@Component
public class WebSocket {
    // 当前在线连接数
    private int onlineCount = 0;

    // 线程安全Set，用来存放每个客户端对应的Session对象。
    private CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<>();

    // session指向用户的map表
    private HashMap<Session, User> sessionToUser = new HashMap<>();

    // 用户指向session的map表
    private HashMap<User, Session> userToSession = new HashMap<>();

    public Timestamp timestamp;
//    @Autowired
//    private LoginMapper loginMapper;

    /*
     * 连接建立成功调用的方法
     */
//    @OnOpen
//    public void onOpen(@PathParam("userId") String userName,
//                       @PathParam("password") String password, Session session) {
//        onlineCount++;
//        System.out.println(loginMapper);
//        System.out.println(loginMapper);
//        try {
//            //userUtil.login(userName,password);
//            System.out.println(userUtil.loginMapper);
//            if(userUtil.login(userName,password) == 0) {
//                 // 返回失败消息 写在service里面 @do
//                return;
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//
//        }
//
//        int userId = UserUtil.getUserId(userName);
//        User user = new User(session, userId);
//        sessions.add(session);
//        sessionToUser.put(session, user);
//        userToSession.put(user, session);
//        System.out.println("有新连接加入！当前在线人数为" + onlineCount);
//
//
//        // User 登录之后的service @do
//
//
//    }
    @OnOpen
    public void onOpen(@PathParam("userId") String userName,
                       @PathParam("password") String password, Session session) throws IOException {

        if(userName.equals("1") && password.equals("1")) {
            int userId = 123;
            loginRegister(session, userId);
            successLogin(userName, session, userId);
        } else if (userName.equals("aaa") && password.equals("aaa")) {
            int userId = 456;
            loginRegister(session, userId);
            successLogin(userName, session, userId);
        } else {
            failLogin(userName, session);
        }
    }

    /*
     * 用于注册含session的成员变量
     */
    private void loginRegister(Session session,int userId) {
        onlineCount++;
        User user = new User(session, userId);
        sessions.add(session);
        sessionToUser.put(session, user);
        userToSession.put(user, session);
        System.out.println("有新连接加入！当前在线人数为" + onlineCount);
    }

    /*
     * 用来生成成功登录消息的util
     */
    private void successLogin(String userName, Session session, int userId) throws IOException {
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
    private void failLogin(String userName, Session session) throws IOException {
        JSONObject message = new JSONObject();
        message.put("type","login");
        message.put("success", false);
        sendMessage(message.toString(), session);
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