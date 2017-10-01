package com.shixizhi.dao;

import com.shixizhi.entity.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

@Repository
public interface ArticleDao {

    @Select("select a.id, a.content, a.date, a.summary, a.title, " +
            "a.category_id from article a, category c " +
            "where a.category_id=c.id and c.name=#{name}")
    public List<Article> findAllByCategory_Name(String name);

    @Select("select * from article where title like #{title}")
    public List<Article> findByTitleLike(String title);

    @Select("select * from article where id=#{id}")
    public Article findOne(String id);

    @Select("select * from article")
    public List<Article> findAll();

    @Delete("delect * from article where id=#{id}")
    public void delete(String id);

    @Insert("insert into article (content, date, summary, title, category_id) " +
            "values (#{content}, #{date}, #{summary}, #{title}, #{category})")
    public void save(Article article);
}