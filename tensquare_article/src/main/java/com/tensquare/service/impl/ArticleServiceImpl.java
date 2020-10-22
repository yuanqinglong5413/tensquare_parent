package com.tensquare.service.impl;

import com.tensquare.model.Article;
import com.tensquare.repository.ArticleRepository;
import com.tensquare.service.ArticleService;
import com.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    IdWorker idWorker;

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article findById(String articleId) {
        Article article = articleRepository.findById(articleId).get();
        return article;
    }

    @Override
    public void save(Article article) {

        article.setId(idWorker.nextId()+"");
        articleRepository.save(article);
    }

    @Override
    public void update(Article article) {

        articleRepository.save(article);
    }

    @Override
    public void deleteById(String articleId) {
        articleRepository.deleteById(articleId);

    }

    @Override
    public List<Article> search(Article article) {
        List<Article> list = articleRepository.findAll(new Specification<Article>() {

            @Nullable
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                List <Predicate> lists = new ArrayList<>();
                if (article != null && !"".equals(article.getTitle())){
                    predicate = criteriaBuilder.like(root.get("title").as(String.class), "%" + article.getTitle() + "%");
                    lists.add(predicate);
                }
                if (article != null && !"".equals(article.getId())){

                    predicate = criteriaBuilder.like(root.get("id").as(String.class), "%" + article.getId() + "%");
                    lists.add(predicate);
                }
                Predicate[] array = lists.toArray(new Predicate[lists.size()]);
                return criteriaBuilder.and(array);
            }
        });
        return list;
    }

    @Override
    public Page<Article> searchPage(Article article, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo-1, size);
        Page<Article> page1 = articleRepository.findAll(new Specification <Article>() {
            Predicate predicate = null;
            @Nullable
            @Override
            public Predicate toPredicate(Root <Article> root, CriteriaQuery <?> query, CriteriaBuilder criteriaBuilder) {
                List <Predicate> listl = new ArrayList <>();
                if (article != null && !"".equals(article.getTitle())){
                    predicate = criteriaBuilder.like(root.get("title").as(String.class), "%" + article.getTitle() + "%");
                    listl.add(predicate);
                }
                if (article != null && !"".equals(article.getId())){

                    predicate = criteriaBuilder.like(root.get("id").as(String.class), "%" + article.getId() + "%");
                    listl.add(predicate);
                }
                Predicate[] array = listl.toArray(new Predicate[listl.size()]);
                return criteriaBuilder.and(array);
            }
        },pageable);
        return page1;
    }

    @Override
    public void updateState(String articleId) {

    }

    @Override
    public void addThumbup(String articleId) {

    }

    @Override
    public Article getIsTop() {
        return null;
    }

    @Override
    public Page<Article> channelIdPage(String channelid, Integer pageNo, Integer size) {
        return null;
    }

    @Override
    public Page<Article> columnidPage(String columnid, Integer pageNo, Integer size) {
        return null;
    }

    public List<Article> selectByIds(List<String> userLikeArticleIds) {
        return articleRepository.selectByIds(userLikeArticleIds);
    }
}
