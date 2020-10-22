package com.tensquare.service;

import java.util.List;

public interface RedisService {

    /**
     * 用户点赞某篇文章
     *
     * @param likedUserId 被点赞用户ID
     * @param likedPostId 点赞用户
     * @param articleId   文章ID
     */
    void likeArticle(String articleId, String likedUserId, String likedPostId);

    /**
     * 取消点赞
     *
     * @param likedUserId 被点赞用户ID
     * @param likedPostId 点赞用户
     * @param articleId   文章ID
     */
    void unlikeArticle(String articleId, String likedUserId, String likedPostId);

    /**
     * 统计某篇文章总点赞数
     *
     * @param articleId
     * @return
     */
    String countArticleLike(String articleId);

    /**
     * 统计用户总的文章点赞数
     *
     * @param likedPoseId
     * @return
     */
    String countUserLike(String likedPoseId);

    /**
     * 获取用户点赞的文章
     *
     * @param likedPostId 点赞用户ID
     * @return
     */
    List<String> getUserLikeArticleIds(String likedPostId);

}

