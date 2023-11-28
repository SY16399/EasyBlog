package org.example.service;

import org.example.mapper.MyArticleMapper;
import org.example.pojo.MyArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    MyArticleMapper myArticleMapper;

    //创建文章
    public boolean add(MyArticle myArticle) {
        long unixTime = System.currentTimeMillis() / 1000L;
        int nowUnixTime = (int) unixTime;
        myArticle.setCreated(nowUnixTime);
        myArticle.setModified(nowUnixTime);
        return myArticleMapper.add(myArticle);
    }
    //获取文章列表 不分页
    public List<MyArticle> findAll() {
        return myArticleMapper.findAll();
    }
    //获取文章列表 分页
    public List<MyArticle> getListByPageNum(int pageNum) {
        if (pageNum <= 0){
            pageNum = 1;
        }
        int offset = (pageNum-1)*30;
        return myArticleMapper.getListByPageNum(offset);
    }
    //文章详情
    public MyArticle detail(int id) {
        return myArticleMapper.detail(id);
    }
    //修改文章
    public boolean update(MyArticle myArticle){
        return myArticleMapper.update(myArticle);
    }
}
