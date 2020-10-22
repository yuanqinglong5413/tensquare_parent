package com.tensquare.repository;

import com.tensquare.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,String>, JpaSpecificationExecutor<Reply> {

    //通过problemId查询回答列表
    @Query(value = "select * from tb_reply where id = problemId ", nativeQuery = true)
    public List<Reply> findByproblemId(String problemId);
}
