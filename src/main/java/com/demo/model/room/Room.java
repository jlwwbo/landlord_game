package com.demo.model.room;

import com.demo.entity.UserEntity;

import java.util.List;

public class Room {
    public static final int PLAYER_NUMBER = 3;

    public int RoomId;
    public List<Integer> MatchIds;
    public UserEntity[] users;



}
