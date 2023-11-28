package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.MyArticle;

import java.util.List;

@Mapper
public interface MyArticleMapper {

    @Insert("INSERT INTO my_articles(title, author, content, category_id, tages, created,modified) VALUES (#{title},#{author},#{content},#{categoryId},#{tags},#{created},#{modified})")
    boolean add(MyArticle myArticle);

    //查询文章列表 不分页
    @Select("select * from my_articles where is_deleted = 1")
    List<MyArticle> findAll();

    //查询文章列表 分页
    @Select("select * from my_articles where is_deleted = 1 limit #{offset},30")
    List<MyArticle> getListByPageNum(int offset);
    //获取文章详情
    @Select("select * from my_articles where id = #{id}")
    MyArticle detail(int id);
}
