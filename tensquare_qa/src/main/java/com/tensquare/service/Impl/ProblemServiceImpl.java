package com.tensquare.service.Impl;

import com.tensquare.model.Problem;
import com.tensquare.repository.ProblemRepository;
import com.tensquare.service.ProblemService;
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
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    IdWorker idWorker;

    @Override
    public List<Problem> findAll() {
        return problemRepository.findAll();
    }

    @Override
    public Problem findById(String problemId) {
        Problem problem = problemRepository.findById(problemId).get();
        return problem;
    }

    @Override
    public void add(Problem problem) {

        problem.setId(idWorker.nextId()+"");
        problemRepository.save(problem);
    }

    @Override
    public void update(Problem problem) {
        problemRepository.save(problem);
    }

    @Override
    public void delete(String problemId) {
        problemRepository.deleteById(problemId);
    }

    @Override
    public List<Problem> search(Problem problem) {
        List<Problem> list = problemRepository.findAll(new Specification<Problem>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Problem> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                List <Predicate> lists = new ArrayList<>();
                if (problem != null && !"".equals(problem.getContent())){
                    predicate = criteriaBuilder.like(root.get("content").as(String.class), "%" + problem.getContent() + "%");
                    lists.add(predicate);
                }
                if (problem != null && !"".equals(problem.getId())){
                    predicate = criteriaBuilder.like(root.get("id").as(String.class), "%" + problem.getId() + "%");
                    lists.add(predicate);
                }
                Predicate[] array = lists.toArray(new Predicate[lists.size()]);
                return criteriaBuilder.and(array);
            }
        });

        return list;
    }

    @Override
    public Page searchPage(Problem problem, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Problem> page1 = problemRepository.findAll(new Specification <Problem>() {
            Predicate predicate = null;
            @Nullable
            @Override
            public Predicate toPredicate(Root <Problem> root, CriteriaQuery <?> query, CriteriaBuilder criteriaBuilder) {
                List <Predicate> lists = new ArrayList <>();
                if (problem != null && !"".equals(problem.getContent())){
                    predicate = criteriaBuilder.like(root.get("content").as(String.class), "%" + problem.getContent() + "%");
                    lists.add(predicate);
                }
                if (problem != null && !"".equals(problem.getId())){
                    predicate = criteriaBuilder.like(root.get("id").as(String.class), "%" + problem.getId() + "%");
                    lists.add(predicate);
                }
                Predicate[] array = lists.toArray(new Predicate[lists.size()]);
                return criteriaBuilder.and(array);
            }

        },pageable);

        return page1;
    }

    @Override
    public Page<Problem> newList(String label, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page <Problem> page1 = problemRepository.newList(label, pageable);
        return page1;
    }

    @Override
    public Page<Problem> hostList(String label, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page <Problem> page1 = problemRepository.hostList(label, pageable);
        return page1;
    }

    @Override
    public Page<Problem> waitList(String label, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page <Problem> page1 = problemRepository.waitList(label, pageable);
        return page1;
    }

    @Override
    public Page<Problem> getAll(String label, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page <Problem> pagel = problemRepository.getAll(label, pageable);
        return pagel;
    }
}
