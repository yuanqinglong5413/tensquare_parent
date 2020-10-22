package com.tensquare.service.impl;

import com.tensquare.model.UserLikeArticle;
import com.tensquare.repository.UserLikeArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLikeArticleServiceImpl {

    @Autowired
    UserLikeArticleRepository userLikeArticleRepository;

    public UserLikeArticle findByUserIdArticleId(String userId, String articleId){
        return userLikeArticleRepository.findByUserIdArticleId(userId,articleId);
    }

    public void save(UserLikeArticle userLikeArticle) {
        userLikeArticleRepository.save(userLikeArticle);
    }

    public void deleteById(String articleId) {
        userLikeArticleRepository.deleteByArticleid(articleId);
    }
}
