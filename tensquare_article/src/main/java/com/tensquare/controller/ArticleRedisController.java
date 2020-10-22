package com.tensquare.controller;


import com.tensquare.service.ArticleService;
import com.tensquare.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleRedisController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private ArticleService articleService;

    /**
     * 点赞文章
     */
    @PostMapping("/{articleId}/{likedUserId}/{likedPoseId}")
    public Object likeArticle(@PathVariable("articleId") String articleId,
                              @PathVariable("likedUserId") String likedUserId,
                              @PathVariable("likedPoseId") String likedPoseId) {
        redisService.likeArticle(articleId, likedUserId, likedPoseId);
        return articleId;
    }

    /**
     * 取消点赞
     */
    @DeleteMapping("/{articleId}/{likedUserId}/{likedPoseId}")
    public Object unlikeArticle(@PathVariable("articleId") String articleId,
                                @PathVariable("likedUserId") String likedUserId,
                                @PathVariable("likedPoseId") String likedPoseId) {
        redisService.unlikeArticle(articleId, likedUserId, likedPoseId);
        return articleId;
    }

    /**
     * 获取用户点赞的文章
     */
    @GetMapping("/user/{likedPoseId}/like")
    public Object getUserLikeArticle(@PathVariable("likedPoseId") String likedPoseId) {
        return articleService.selectByIds(redisService.getUserLikeArticleIds(likedPoseId));
    }

    /**
     * 统计用户总的文章点赞数
     */
    @GetMapping("/user/total/{likedPoseId}")
    public Object countUserLike(@PathVariable("likedPoseId") String likedPoseId) {
        return redisService.countUserLike(likedPoseId);
    }

    /**
     * 统计某篇文章总点赞数
     */
    @GetMapping("/article/total/{articleId}")
    public Object countArticleLike(@PathVariable("articleId") String articleId) {
        return redisService.countArticleLike(articleId);
    }
}
