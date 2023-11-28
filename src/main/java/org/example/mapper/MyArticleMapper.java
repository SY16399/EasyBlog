package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.MyArticle;

@Mapper
public interface MyArticleMapper {

    @Insert("INSERT INTO my_articles(title, author, content, category_id, tages, created,modified) VALUES (#{title},#{author},#{content},#{categoryId},#{tags},#{created},#{modified})")
    boolean add(MyArticle myArticle);
}
