package com.tensquare.controller;


import com.tensquare.model.Article;
import com.tensquare.service.ArticleService;
import com.tensquare.util.PageResult;
import com.tensquare.util.ResultObject;
import com.tensquare.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    //查询全部
    @GetMapping
    public ResultObject getAll(){
        //调用业务层
        List<Article> list = articleService.findAll();
        return new ResultObject(StatusCode.OK,"查询全部成功",true,list);
    }

    //根据id查询
    @GetMapping("/{articleId}")
    public ResultObject findById(@PathVariable String articleId){
        //调用业务层
        Article article = articleService.findById(articleId);
        return new ResultObject(StatusCode.OK,"查询id成功",true,article);
    }
    //添加文章
    @PostMapping
    public ResultObject save(@RequestBody Article article){
        articleService.save(article);
        return new ResultObject(StatusCode.OK,"添加成功",true);
    }

    //修改文章
    @PutMapping("/{articleId}")
    public ResultObject updateById(@RequestBody Article article,@PathVariable String articleId){
        article.setId(articleId);
        //调用业务层
        articleService.update(article);
        return new ResultObject(StatusCode.OK,"修改成功",true);
    }

    //删除文章
    @DeleteMapping("/{articleId}")
    public ResultObject deleteById(@PathVariable String articleId){
        //调用业务层
        articleService.deleteById(articleId);
        return new ResultObject(StatusCode.OK,"删除成功",true);
    }

    //模糊查询文章
    @PostMapping("/search")
    public ResultObject search(@RequestBody Article article){
        //调用业务层
        List<Article> list = articleService.search(article);
        return new ResultObject(StatusCode.OK,"模糊查询成功",true,list);
    }

    //模糊查询分页文章
    @PostMapping("/search/{pageNo}/{size}")
    public ResultObject searchPage(@RequestBody Article article,@PathVariable Integer pageNo,@PathVariable Integer size){
        //调用业务层
        Page<Article> page = articleService.searchPage(article,pageNo,size);
        return new ResultObject(StatusCode.OK,
                "模糊查询分页成功",
                true,
                new PageResult<Article>(page.getTotalElements(),page.getContent()));
    }

    //审核
    @PutMapping(value = "/examine/{articleId}")
    public ResultObject examine(@PathVariable String articleId){
        articleService.updateState(articleId);
        return new ResultObject(StatusCode.OK, "审核成功",true);
    }

    //点赞
    @PutMapping(value = "/thumbup/{articleId}")
    public ResultObject thumbup(@PathVariable String articleId){
        articleService.addThumbup(articleId);
        return new ResultObject(StatusCode.OK, "点赞成功",true);
    }

    //头条置顶
    @GetMapping("/top")
    public ResultObject getIsTop(){
        //调用业务层
        Article article = articleService.getIsTop();
        return new ResultObject(StatusCode.OK,"查询全部成功",true,article);
    }

    //根据频道id获取文章列表
    @PostMapping("/channel/{channelId}/{page}/{size}")
    public ResultObject channelIdPage(@PathVariable String channelid,@PathVariable Integer pageNo,@PathVariable Integer size){
        //调用业务层
        Page<Article> page = articleService.channelIdPage(channelid,pageNo,size);
        return new ResultObject(StatusCode.OK,
                "模糊查询分页成功",
                true,
                new PageResult<Article>(page.getTotalElements(),page.getContent()));
    }

    //根据专栏ID获取文章列表
    @PostMapping("/column/{columnId}/{page}/{size}")
    public ResultObject columnidPage(@PathVariable String columnid,@PathVariable Integer pageNo,@PathVariable Integer size){
        //调用业务层
        Page<Article> page = articleService.columnidPage(columnid,pageNo,size);
        return new ResultObject(StatusCode.OK,
                "模糊查询分页成功",
                true,
                new PageResult<Article>(page.getTotalElements(),page.getContent()));
    }
}
