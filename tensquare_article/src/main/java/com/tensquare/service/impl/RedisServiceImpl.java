package com.tensquare.service.impl;

import com.tensquare.service.RedisService;
import com.tensquare.util.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RedisServiceImpl implements RedisService {

    Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);
    /**
     * 文章点赞总数key
     * redis key命名规范推荐使用大写，单词与单词之间使用:
     */
    @Value("${total.like.count.key}")
    private String TOTAL_LIKE_COUNT_KEY;

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
    private RedisTemplate redisTemplate;

    /**
     * 指定序列化方式
     */
    @PostConstruct
    public void init() {
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
    }

    /**
     * 用户点赞某篇文章
     *
     * @param likedUserId 被点赞用户ID
     * @param likedPostId 点赞用户
     * @param articleId   文章ID
     */
    public void likeArticle(String articleId, String likedUserId, String likedPostId) {
        validateParam(articleId, likedUserId, likedPostId);  //参数验证

        logger.info("点赞数据存入redis开始，articleId:{}，likedUserId:{}，likedPostId:{}", articleId, likedUserId, likedPostId);

        //只有未点赞的用户才可以进行点赞
        likeArticleLogicValidate(articleId, likedUserId, likedPostId);
        //1.被点赞用户的总点赞数+1 --> 当Redis中没有该key,即第一次执行此命令时,会创建该key并进行自增
        redisTemplate.opsForHash().increment(TOTAL_LIKE_COUNT_KEY, likedUserId, 1);

        //2.当前用户喜欢的文章+1
        String userLikeResult = (String) redisTemplate.opsForHash().get(USER_LIKE_ARTICLE_KEY, likedPostId);
        Set<String> articleIdSet = userLikeResult == null ? new HashSet<>() : FastjsonUtil.deserializeToSet(userLikeResult, String.class);
        articleIdSet.add(articleId);
        redisTemplate.opsForHash().put(USER_LIKE_ARTICLE_KEY, likedPostId, FastjsonUtil.serialize(articleIdSet));

        //3.文章点赞数+1
        String articleLikedResult = (String) redisTemplate.opsForHash().get(ARTICLE_LIKED_USER_KEY, articleId);
        Set<String> likePostIdSet = articleLikedResult == null ? new HashSet<>() : FastjsonUtil.deserializeToSet(articleLikedResult, String.class);
        likePostIdSet.add(likedPostId);
        redisTemplate.opsForHash().put(ARTICLE_LIKED_USER_KEY, articleId, FastjsonUtil.serialize(likePostIdSet));
        logger.info("点赞数据存入redis结束，articleId:{}，likedUserId:{}，likedPostId:{}", articleId, likedUserId, likedPostId);
    }

    /**
     * 取消点赞
     *
     * @param likedUserId 被点赞用户ID
     * @param likedPostId 点赞用户
     * @param articleId   文章ID
     */
    public void unlikeArticle(String articleId, String likedUserId, String likedPostId) {
        validateParam(articleId, likedUserId, likedPostId);  //参数校验

        logger.info("取消点赞数据存入redis开始，articleId:{}，likedUserId:{}，likedPostId:{}", articleId, likedUserId, likedPostId);
        //1.被点赞用户总点赞数-1
        //只有点赞的用户才可以取消点赞
        unlikeArticleLogicValidate(articleId, likedUserId, likedPostId);
        // 获得被取消喜欢的用户的总点赞数
        Long totalLikeCount = Long.parseLong((String) redisTemplate.opsForHash().get(TOTAL_LIKE_COUNT_KEY, likedUserId));
        // 减1后重新放入
        redisTemplate.opsForHash().put(TOTAL_LIKE_COUNT_KEY, likedUserId, String.valueOf(--totalLikeCount));

        //2.用户点赞的文章-1
        String userLikeResult = (String) redisTemplate.opsForHash().get(USER_LIKE_ARTICLE_KEY, likedPostId);
        Set<String> articleIdSet = FastjsonUtil.deserializeToSet(userLikeResult, String.class);
        articleIdSet.remove(articleId);
        redisTemplate.opsForHash().put(USER_LIKE_ARTICLE_KEY, likedPostId, FastjsonUtil.serialize(articleIdSet));

        //3.取消某篇文章的点赞数
        String articleLikedResult = (String) redisTemplate.opsForHash().get(ARTICLE_LIKED_USER_KEY, articleId);
        Set<String> likePostIdSet = FastjsonUtil.deserializeToSet(articleLikedResult, String.class);
        likePostIdSet.remove(likedPostId);
        redisTemplate.opsForHash().put(ARTICLE_LIKED_USER_KEY, articleId, FastjsonUtil.serialize(likePostIdSet));
        logger.info("取消点赞数据存入redis结束，articleId:{}，likedUserId:{}，likedPostId:{}", articleId, likedUserId, likedPostId);
    }

    /**
     * 统计某篇文章总点赞数
     *
     * @param articleId 文章ID
     * @return
     */
    public String countArticleLike(String articleId) {
        validateParam(articleId);
        String articleLikedResult = (String) redisTemplate.opsForHash().get(ARTICLE_LIKED_USER_KEY, articleId);
        Set<String> likePostIdSet = FastjsonUtil.deserializeToSet(articleLikedResult, String.class);
        if (likePostIdSet == null) {
            return "0";
        }
        return String.valueOf(likePostIdSet.size());
    }

    /**
     * 统计用户总的文章点赞数
     *
     * @param likedUserId
     * @return
     */
    public String countUserLike(String likedUserId) {
        validateParam(likedUserId);
        return String.valueOf((String) redisTemplate.opsForHash().get(TOTAL_LIKE_COUNT_KEY, likedUserId));
    }

    /**
     * 获取用户点赞的文章
     *
     * @param likedPostId
     * @return
     */
    public List<String> getUserLikeArticleIds(String likedPostId) {
        validateParam(likedPostId);
        String userLikeResult = (String) redisTemplate.opsForHash().get(USER_LIKE_ARTICLE_KEY, likedPostId);
        Set<String> articleIdSet = FastjsonUtil.deserializeToSet(userLikeResult, String.class);

        return articleIdSet.stream().collect(Collectors.toList());
    }

    /**
     * 入参验证
     *
     * @param params
     * @throws CustomException
     */
    private void validateParam(String... params) {
        for (String param : params) {
            if (null == param) {
                logger.error("入参存在null值");
                throw new CustomException(StatusCode.ERROR, "入参存在null值");
            }
        }
    }

    /**
     * 点赞文章逻辑校验
     *
     * @throws
     */
    private void likeArticleLogicValidate(String articleId, String likedUserId, String likedPostId) {
        Set<String> articleIdSet = FastjsonUtil.deserializeToSet((String) redisTemplate.opsForHash().get(USER_LIKE_ARTICLE_KEY, likedPostId), String.class);
        Set<String> likePostIdSet = FastjsonUtil.deserializeToSet((String) redisTemplate.opsForHash().get(ARTICLE_LIKED_USER_KEY, articleId), String.class);
        if (articleIdSet == null) {
            return;
        }
        if (likePostIdSet == null) {
            return;
        } else {
            if (articleIdSet.contains(articleId) || likePostIdSet.contains(likedPostId)) {
                logger.error("该文章已被当前用户点赞，重复点赞，articleId:{}，likedUserId:{}，likedPostId:{}", articleId, likedUserId, likedPostId);
                throw new CustomException(StatusCode.ERROR, "重复点赞");
            }
        }
    }

    /**
     * 取消点赞逻辑校验
     */
    private void unlikeArticleLogicValidate(String articleId, String likedUserId, String likedPostId) {
        Set<String> articleIdSet = FastjsonUtil.deserializeToSet((String) redisTemplate.opsForHash().get(USER_LIKE_ARTICLE_KEY, likedPostId), String.class);
        Set<String> likePostIdSet = FastjsonUtil.deserializeToSet((String) redisTemplate.opsForHash().get(ARTICLE_LIKED_USER_KEY, articleId), String.class);
        if (articleIdSet == null || !articleIdSet.contains(articleId)
                || likePostIdSet == null || !likePostIdSet.contains(likedPostId)) {
            logger.error("该文章未被当前用户点赞，不可以进行取消点赞操作，articleId:{}，likedUserId:{}，likedPostId:{}",
                    articleId, likedUserId, likedPostId);
            throw new CustomException(StatusCode.ERROR, "还未点赞,不能取消");
        }
    }
}
