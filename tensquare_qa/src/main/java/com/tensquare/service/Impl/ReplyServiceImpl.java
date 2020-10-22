package com.tensquare.service.Impl;

import com.tensquare.model.Reply;
import com.tensquare.repository.ReplyRepository;
import com.tensquare.service.ReplyService;
import com.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    IdWorker idWorker;

    @Override
    public List<Reply> findAll() {
        return replyRepository.findAll();
    }

    @Override
    public Reply findById(String replyId) {
        Reply reply =  replyRepository.findById(replyId).get();
        return reply;
    }

    @Override
    public void add(Reply reply) {
        reply.setId(idWorker.nextId()+"");
        replyRepository.save(reply);
    }

    @Override
    public void update(Reply reply) {
        replyRepository.save(reply);
    }

    @Override
    public void delete(String replyId) {
        replyRepository.deleteById(replyId);
    }

    @Override
    public List<Reply> findByproblemId(String problemId) {
        Reply reply =  replyRepository.findById(problemId).get();
        return (List<Reply>) reply;
    }

    @Override
    public Page searchPage(Reply reply, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Reply> page1 = replyRepository.findAll(new Specification<Reply>() {
            Predicate predicate = null;
            @Nullable
            @Override
            public Predicate toPredicate(Root<Reply> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List <Predicate> lists = new ArrayList<>();
                if (reply != null && !"".equals(reply.getContent())){
                    predicate = criteriaBuilder.like(root.get("content").as(String.class), "%" + reply.getContent() + "%");
                    lists.add(predicate);
                }
                if (reply != null && !"".equals(reply.getId())){
                    predicate = criteriaBuilder.like(root.get("id").as(String.class), "%" + reply.getId() + "%");
                    lists.add(predicate);
                }
                Predicate[] array = lists.toArray(new Predicate[lists.size()]);
                return criteriaBuilder.and(array);
            }

        },pageable);

        return page1;
    }

    @Override
    public void save(Reply reply) {
        reply.setId(idWorker.nextId()+"");
        replyRepository.save(reply);
    }

    @Override
    public Page<Reply> findAllByPageandSize(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Reply> page1 = replyRepository.findAll(pageable);
        return page1;
    }


}
