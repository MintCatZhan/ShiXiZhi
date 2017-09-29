package com.shixizhi.dao;

import com.shixizhi.entity.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

@Repository
public interface CategoryDao{

    @Select("select * from category where name=#{name}")
    Category findByName(String name);

    @Select("select * from category")
    List<Category> findAll();

    @Select("select * from category where id=#{id}")
    Category findOne(String id);
}
