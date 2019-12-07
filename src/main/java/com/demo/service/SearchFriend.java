package com.demo.service;

import com.alibaba.fastjson.JSONObject;

import javax.websocket.Session;
import java.io.IOException;

import static com.demo.controller.WebSocket.sendMessage;

public class SearchFriend {
    public String nickName;
    public int userId;
    public int friendId;
    public boolean isFriend;
    public int expValue;
    public Session session;

    public boolean success = false;

    public SearchFriend(String nickName, int userId, Session session) {
        this.nickName = nickName;
        this.userId = userId;
        this.session = session;
    }

    /*
     * 搜索好友
     */
    public void search() {
        if(nickName.equals("aaa")){
            success = true;
            friendId = 456;
            isFriend = true;
            expValue = 200;
        } else if (nickName.equals("1")) {
            success = true;
            userId = 123;
            isFriend = false;
            expValue = 300;
        }
    }

    public void feedback() throws IOException {
        if (success){
            successSearchFriend();
        } else {
            failSearchFriend();
        }
    }
    /*
     * 搜索好友成功与失败的返回函数
     */
    public void successSearchFriend() throws IOException {
        JSONObject message = new JSONObject();
        message.put("type","searchFriend");
        message.put("success", true);
        message.put("nickName", nickName);
        message.put("friendId", friendId);
        message.put("isFriend", isFriend);
        message.put("expValue", expValue);

        sendMessage(message.toString(), session);
    }
    public void failSearchFriend() throws IOException {
        JSONObject message = new JSONObject();
        message.put("type","searchFriend");
        message.put("success", false);
        message.put("userName", nickName);
        sendMessage(message.toString(), session);
    }
}
