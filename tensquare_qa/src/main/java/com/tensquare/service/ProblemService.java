package com.tensquare.service;

import com.tensquare.model.Problem;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProblemService {
    List<Problem> findAll();

    Problem findById(String problemId);

    void add(Problem problem);

    void update(Problem problem);

    void delete(String problemId);

    List<Problem> search(Problem problem);

    Page searchPage(Problem problem, Integer page, Integer size);

    Page<Problem> newList(String label, Integer page, Integer size);

    Page<Problem> hostList(String label, Integer page, Integer size);

    Page<Problem> waitList(String label, Integer page, Integer size);

    Page<Problem> getAll(String label, Integer page, Integer size);
}
