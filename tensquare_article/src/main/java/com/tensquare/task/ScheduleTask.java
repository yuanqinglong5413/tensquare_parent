package com.tensquare.task;

import com.tensquare.model.Article;
import com.tensquare.model.UserLikeArticle;
import com.tensquare.service.ArticleService;
import com.tensquare.service.UserLikeArticleService;
import com.tensquare.util.FastjsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;

public class ScheduleTask {

    private Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 用户点赞文章key
     */
    @Value("${user.like.article.key}")
    private String USER_LIKE_ARTICLE_KEY;

    /**
     * 文章被点赞的key
     */
    @Value("${article.liked.user.key}")
    private String ARTICLE_LIKED_USER_KEY;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserLikeArticleService userLikeArticleService;

    @Autowired
    private RedisTemplate redisTemplate;

    // 0 0 0/1 * * ?   1小时
    // 0 */1 * * * ?    1分钟一次
    // 每隔5秒执行一次：*/5 * * * * ?
    @Scheduled(cron = "0 */1 * * * ? ")
    public void redisDataToMySQL() {
        logger.debug("time:{}，开始执行Redis数据持久化到MySQL任务", LocalDateTime.now().format(formatter));
        //1.更新文章总的点赞数
        Map<String, String> articleCountMap = redisTemplate.opsForHash().entries(ARTICLE_LIKED_USER_KEY);
        // 遍历所有文章
        for (Map.Entry<String, String> entry : articleCountMap.entrySet()) {
            String articleId = entry.getKey();
            Set<String> userIdSet = FastjsonUtil.deserializeToSet(entry.getValue(), String.class);
            //1.同步某篇文章总的点赞数到MySQL
            synchronizeTotalLikeCount(articleId, userIdSet);
            //2.同步用户喜欢的文章
            synchronizeUserLikeArticle(articleId, userIdSet);
        }
        logger.debug("time:{}，结束执行Redis数据持久化到MySQL任务", LocalDateTime.now().format(formatter));
    }

    /**
     * 同步某篇文章总的点赞数到MySQL
     */
    private void synchronizeTotalLikeCount(String articleId, Set<String> userIdSet) {
        Article article = new Article();
        article.setId(articleId);
        article.setThumbup(userIdSet.size());
        articleService.update(article);
    }

    /**
     * 同步用户喜欢的文章
     *
     * @param articleId
     * @param userIdSet
     */
    private void synchronizeUserLikeArticle(String articleId, Set<String> userIdSet) {
        System.out.println("同步用户喜欢的文章 userIdSet = " + userIdSet);
        if (userIdSet.size() > 0) {
            for (String userId : userIdSet) {
                UserLikeArticle userLikeArticle = new UserLikeArticle();
                userLikeArticle.setArticleId(articleId);
                userLikeArticle.setUserId(userId);
                if (userLikeArticleService.findByUserIdArticleId(userId,articleId) == null) {
                    userLikeArticleService.save(userLikeArticle);
                }
            }
        }else{
            userLikeArticleService.deleteById(articleId);
        }

    }
}
