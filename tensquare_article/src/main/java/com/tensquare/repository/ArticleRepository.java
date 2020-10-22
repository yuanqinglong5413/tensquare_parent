package com.tensquare.repository;

import com.tensquare.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,String>, JpaSpecificationExecutor<Article> {

    @Modifying // 不加报错,JPA 默认操作,增删改时使用, 在调用的地方必须加事务，没有事务不能正常执行
    @Query(value = "UPDATE tb_article SET state=1 WHERE id = ?", nativeQuery = true)
    public void updateState(String id);

    @Modifying
    @Query(value = "UPDATE tb_article SET thumbup=thumbup+1 WHERE id = ?", nativeQuery = true)
    public void addThumbup(String id);

    @Query(value = "select * from tb_article where id in (:ids)", nativeQuery = true)
    List<Article> selectByIds(@Param("ids") List<String> userLikeArticleIds);
}
