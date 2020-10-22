package com.tensquare.service;

import com.tensquare.model.Reply;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReplyService {
    List<Reply> findAll();

    Reply findById(String replyId);

    void add(Reply reply);

    void update(Reply reply);

    void delete(String replyId);

    List<Reply> findByproblemId(String problemId);

    Page searchPage(Reply reply, Integer page, Integer size);

    void save(Reply reply);


    Page<Reply> findAllByPageandSize(Integer page, Integer size);
}
