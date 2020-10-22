package com.tensquare.controller;

import com.tensquare.mondel.Article;
import com.tensquare.service.ArticleService;
import com.tensquare.util.PageResult;
import com.tensquare.util.ResultObject;
import com.tensquare.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.POST)
    public ResultObject save(@RequestBody Article article) {
        articleService.save(article);
        return new ResultObject(StatusCode.OK, "添加成功", true);
    }

    @RequestMapping(value = "/{key}/{page}/{size}", method = RequestMethod.GET)
    public ResultObject findByKey(@PathVariable String key, @PathVariable int page, @PathVariable int size){
        Page<Article> pageData = articleService.findByKey(key, page, size);
        return new ResultObject( StatusCode.OK, "查询成功",true, new PageResult<Article>(pageData.getTotalElements(), pageData.getContent()));
    }
}
