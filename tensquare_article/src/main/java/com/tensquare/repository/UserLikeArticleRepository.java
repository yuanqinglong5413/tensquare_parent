package com.tensquare.repository;

import com.tensquare.model.UserLikeArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserLikeArticleRepository extends JpaRepository<UserLikeArticle,String> {

    @Query(value = "select * from tb_user_like_article where user_id = ?1 and article_id = ?2", nativeQuery = true)
    UserLikeArticle findByUserIdArticleId(String userId, String articleId);


    @Modifying
    @Query(value = "delete from tb_user_like_article where article_id = ?", nativeQuery = true)
    void deleteByArticleid(String articleId);
}
