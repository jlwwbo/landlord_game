package com.demo.mapper;

import com.demo.entity.LoginEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface LoginMapper {
    /*
     * 登录核心函数。
     * 判断登录的用户名和密码是否合法
     * @return UserId / null when not found
     */
    @Select("select userId from login where username=#{userName} and password=#{password}")
    int loginGetUserId(String userName, String password);

    @Select("select userId from login where username=#{userName}")
    int searchUser(String userName);
}
