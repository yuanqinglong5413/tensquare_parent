package com.tensquare.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_user_like_article")
public class UserLikeArticle {

    @Column(name="user_id")
    @Id
    private String userId;
    @Column(name="article_id")
    private String articleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
