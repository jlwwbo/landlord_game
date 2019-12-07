package com.demo.model.user;

import javax.websocket.Session;

public class User {
    public int userID;
    public String userName;
    public Session session;


    /*
     * 构造函数
     * @params session of user
     */
    public User(Session session, int userId) {
        this.session = session;
        this.userID = userId;
        // @do 还有其他东西
    }


}
