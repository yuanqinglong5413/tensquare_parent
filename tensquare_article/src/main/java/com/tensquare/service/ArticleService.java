package com.tensquare.service;

import com.tensquare.model.Article;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {
    List<Article> findAll();

    Article findById(String articleId);

    void save(Article article);

    void update(Article article);

    void deleteById(String articleId);

    List<Article> search(Article article);

    Page<Article> searchPage(Article article, Integer pageNo, Integer size);

    void updateState(String articleId);

    void addThumbup(String articleId);

    Article getIsTop();

    Page<Article> channelIdPage(String channelid, Integer pageNo, Integer size);

    Page<Article> columnidPage(String columnid, Integer pageNo, Integer size);

}
