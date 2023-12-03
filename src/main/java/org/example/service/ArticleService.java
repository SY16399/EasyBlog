package org.example.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.example.mapper.MyArticleMapper;
import org.example.pojo.MyArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    MyArticleMapper myArticleMapper;
    //使用 mongodb
    @Autowired
    MongoTemplate mongoTemplate;

    //创建文章
    public boolean add(MyArticle myArticle) {
        long unixTime = System.currentTimeMillis() / 1000L;
        int nowUnixTime = (int) unixTime;
        myArticle.setCreated(nowUnixTime);
        myArticle.setModified(nowUnixTime);
        //mongodb
        MyArticle obj= mongoTemplate.save(myArticle);
        //mysql
        boolean add = myArticleMapper.add(myArticle);
        if (obj != null){
            return add;
        }else {
            return false;
        }

    }
    //获取文章列表 不分页
    public List<MyArticle> findAll() {
        //查的到 mongo 查 mongo 查不到 查 mysql
        //更新 mongo没做
        List<MyArticle> lists = mongoTemplate.findAll(MyArticle.class);
        if (!lists.isEmpty()){
            return lists;
        }
        return myArticleMapper.findAll();
    }
    //获取文章列表 分页
    public List<MyArticle> getListByPageNum(int pageNum) {
        //mongo
        Query query = new Query();
        query.skip(pageNum* 30L);
        query.limit(30);
        List<MyArticle> lists = mongoTemplate.find(query,MyArticle.class);
        if (!lists.isEmpty()){
            return lists;
        }
        //mysql
        if (pageNum <= 0){
            pageNum = 1;
        }
        int offset = (pageNum-1)*30;
        return myArticleMapper.getListByPageNum(offset);
    }
    //文章详情
    public MyArticle detail(int id) {
        //mongo
        Query query = Query.query(Criteria.where("id").is(id));
        MyArticle one = mongoTemplate.findOne(query, MyArticle.class);
        if (one != null){
            return one;
        }
        //mysql
        return myArticleMapper.detail(id);
    }

    public boolean update(MyArticle myArticle){
        long unixTime = System.currentTimeMillis() / 1000L;
        int nowUnixTime = (int) unixTime;
        //mongo
        Query query = new Query(Criteria.where("id").is(myArticle.getId()));
        Update update = new Update();
        update.set("title",myArticle.getTitle());
        update.set("author",myArticle.getAuthor());
        update.set("content",myArticle.getContent());
        update.set("categoryId",myArticle.getCategoryId());
        update.set("tags",myArticle.getTags());
        update.set("is_deleted",myArticle.getIs_deleted());
        update.set("modified",nowUnixTime);
        UpdateResult updateResult1 = mongoTemplate.updateFirst(query,update, MyArticle.class);
        //mysql
        myArticle.setModified(nowUnixTime);
        boolean update2 = myArticleMapper.update(myArticle);


        if (updateResult1.getModifiedCount() > 0){
            return update2;
        }
        return false;
    }

    //删除
    public boolean delet(int id){
        //mongo
        MyArticle myArticle = new MyArticle();
        myArticle.setId(id);
        DeleteResult deleteResult1 = mongoTemplate.remove(myArticle);
        //mysql
        boolean deleteResult2 = myArticleMapper.delete(id);
        if (deleteResult1.getDeletedCount()>0){
            return deleteResult2;
        }
        return false;
    }
}
