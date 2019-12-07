//package com.demo.model.user;
//
//import com.demo.entity.LoginEntity;
//import com.demo.mapper.LoginMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserUtil {
//    @Autowired
//    public LoginMapper loginMapper;
//    /*
//     * 根据用户名，返回userId
//     */
//    public static int getUserId(String userName) {
//
//        // @do
//        return 0;
//    }
//
//
//    public int login(String userName, String password) {
//        System.out.println(loginMapper);
//        LoginEntity loginEntity = loginMapper.getLoginEntity(userName,password);
//        return loginEntity.getUserId();
//    }
//}
