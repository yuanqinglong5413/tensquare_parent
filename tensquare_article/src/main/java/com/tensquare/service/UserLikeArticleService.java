package com.tensquare.service;

import com.tensquare.model.UserLikeArticle;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserLikeArticleService {

    public UserLikeArticle findByUserIdArticleId(String userId, String articleId);

    public void save(UserLikeArticle userLikeArticle);

    public void deleteById(String articleId);
}
