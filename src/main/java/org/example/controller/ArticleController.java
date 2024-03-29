package org.example.controller;

import org.example.anno.UserLoginToken;
import org.example.pojo.MyArticle;
import org.example.service.ArticleService;
import org.example.tools.ErrorEnum;
import org.example.tools.JsonResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @UserLoginToken
    @PostMapping("/article")
    //防止传入空参  默认会把处理的结果传给BindingResult对象。
    public JsonResultObject add(@Validated @RequestBody MyArticle myArticle, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return new JsonResultObject("400", "新发布文章失败！", bindingResult.getFieldError().getDefaultMessage(), "Bad Params", null);
            }
            boolean addResult = articleService.add(myArticle);
            if (addResult) {
                return new JsonResultObject("200", "新发布文章成功", "", "", null);
            } else {
                ErrorEnum enum1 = ErrorEnum.valueOf(ErrorEnum.class, "BAD_INPUT_PARAM");
                return new JsonResultObject("200", "新发布文章失败", enum1.getErrorMsg(), enum1.getErrorCode(), null);
            }
        } catch (Exception e) {
            return new JsonResultObject<>("400", "发布最新文章失败！", e.getMessage(), "Bad Params", null);
        }
        /*boolean addResult = articleService.add(myArticle);
        if (addResult){
            return new JsonResultObject("200","新发布文章成功","","",null);
        }else {
            ErrorEnum enum1 = ErrorEnum.valueOf(ErrorEnum.class,"BAD_INPUT_PARAM");
            return new JsonResultObject("200","新发布文章失败",enum1.getErrorMsg(), enum1.getErrorCode(), null);
        }*/
    }

    //获取文章列表 不分页
    @RequestMapping("/articles")
    public JsonResultObject getAll() {
        List<MyArticle> articles = articleService.findAll();
        JsonResultObject result = new JsonResultObject("200", "get   articles", "", "", articles);
        return result;
    }

    //获取文章列表 不分页
    @RequestMapping("/articles/{pageNum}")
    public JsonResultObject getListByPageNum(@PathVariable int pageNum) {
        List<MyArticle> articles = articleService.getListByPageNum(pageNum);
        JsonResultObject result = new JsonResultObject("200", "get   articles", "", "", articles);
        return result;

    }

    //获取文章详情的接口
    @RequestMapping("/article/{id}")
    public JsonResultObject detail(@PathVariable int id) {
        MyArticle article = articleService.detail(id);
        JsonResultObject result = new JsonResultObject("200", "get   articles", "", "", article);
        return result;
    }

    //修改文章
    @PutMapping("/article")
    @UserLoginToken
    public JsonResultObject update(@Validated @RequestBody MyArticle myArticle, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new JsonResultObject<>("400", "修改文章失败！", bindingResult.getFieldError().getDefaultMessage(), "40002", null);
        } else {
            if (myArticle.getId() == 0) {
                return new JsonResultObject("400", "文章修改失败！", "no Id", "40003", null);
            } else {
                boolean updateResult = articleService.update(myArticle);
                if (updateResult) {
                    return new JsonResultObject<>("200", "修改文章成功", "", "", null);
                } else {
                    ErrorEnum enum1 = ErrorEnum.valueOf(ErrorEnum.class, "BAD_PARAM");
                    return new JsonResultObject("200", "修改文章失败", enum1.getErrorMsg(), enum1.getErrorCode(), null);
                }
            }
        }
    }

    //Delet
    @UserLoginToken
    @DeleteMapping("/article/{id}")
    public JsonResultObject delete(@PathVariable int id) {
        boolean deleteResult = articleService.delet(id);
        if (deleteResult) {
            return new JsonResultObject("200", "删除文章成功", "", "", null);
        } else {
            ErrorEnum enum1 = ErrorEnum.valueOf(ErrorEnum.class, "BAD_PARAM");
            return new JsonResultObject("200", "删除文章失败！", enum1.getErrorMsg(), enum1.getErrorCode(), null);
        }
    }
}
