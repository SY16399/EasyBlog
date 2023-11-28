package org.example.service;

import org.example.mapper.MyArticleMapper;
import org.example.pojo.MyArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
