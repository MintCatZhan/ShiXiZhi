package com.shixizhi.dao;

import com.shixizhi.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao{

    @Select("select * from User u where u.username = #{username} and password = #{password}")
    User findByUsernameAndPassword(String username, String password);
}