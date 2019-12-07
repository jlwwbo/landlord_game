package com.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "loginMapper")
public interface LoginMapper {
    /*
     * 登录核心函数。
     * 判断登录的用户名和密码是否合法
     * @return UserId / null when not found
     */
    @Select("select userId from login where userName=#{userName} and pwd=#{password}")
    int loginGetUserId(String userName, String password);

    @Select("select count(*) from login where userName=#{name}")
    boolean userExist(String name);

    @Select("select count(*) from login where userName=#{name} and pwd=#{pwd}")
    boolean rightPassword(String name, String pwd);
}
