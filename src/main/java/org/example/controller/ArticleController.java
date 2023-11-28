package org.example.controller;

import org.example.pojo.MyArticle;
import org.example.service.ArticleService;
import org.example.tools.ErrorEnum;
import org.example.tools.JsonResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping("/article")
    //防止传入空参  默认会把处理的结果传给BindingResult对象。
    public JsonResultObject add(@Validated @RequestBody MyArticle myArticle, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()){
                return new JsonResultObject("400","新发布文章失败！",bindingResult.getFieldError().getDefaultMessage(),"Bad Params",null);
            }
            boolean addResult = articleService.add(myArticle);
            if (addResult){
                return new JsonResultObject("200","新发布文章成功","","",null);
            }else {
                ErrorEnum enum1 = ErrorEnum.valueOf(ErrorEnum.class,"BAD_INPUT_PARAM");
                return new JsonResultObject("200","新发布文章失败",enum1.getErrorMsg(), enum1.getErrorCode(), null);
            }
        }catch (Exception e){
            return new JsonResultObject<>("400","发布最新文章失败！", e.getMessage(), "Bad Params",null);
        }
        /*boolean addResult = articleService.add(myArticle);
        if (addResult){
            return new JsonResultObject("200","新发布文章成功","","",null);
        }else {
            ErrorEnum enum1 = ErrorEnum.valueOf(ErrorEnum.class,"BAD_INPUT_PARAM");
            return new JsonResultObject("200","新发布文章失败",enum1.getErrorMsg(), enum1.getErrorCode(), null);
        }*/
    }
}
