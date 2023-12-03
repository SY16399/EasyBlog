package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyArticle implements Serializable {
    //文章ID
    private int id;
    //文章标题
    //使用注解实现字段检查 ，NotBlank用于验证字符串类型不为空
    @NotBlank(message = "文章标题不能为空")
    private String title;
    //文章作者
    @NotBlank(message = "作者名不能是空")
    private String author;
    //文章内容
    @NotBlank(message = "文章内容不能是空")
    private String content;
    //文章分类ID
    @NotNull(message = "分类ID不能为空")
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
