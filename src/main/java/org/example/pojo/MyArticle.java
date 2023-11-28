package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyArticle {
    //文章ID
    private int id;
    //文章标题
    private String title;
    //文章作者
    private String author;
    //文章内容
    private String content;
    //文章分类ID
    private int categoryId;
    //标签 以逗号分隔
    private String tags;
    //是否删除 1.表示删除 2.表示已删除
    private int is_deleted;
    //创建时间
    private int created;
    //修改时间
    private int modified;
}
