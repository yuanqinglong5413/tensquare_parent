package com.tensquare.repository;

import com.tensquare.model.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ProblemRepository extends JpaRepository<Problem,String>, JpaSpecificationExecutor {

    @Query(value = "SELECT * FROM tb_problem, tb_pl WHERE id = problemid AND labelid=? ORDER BY replytime DESC", nativeQuery = true)
    Page<Problem> newList(String label, Pageable pageable);


    @Query(value = "SELECT * FROM tb_problem, tb_pl WHERE id = problemid AND labelid=? ORDER BY reply DESC", nativeQuery = true)
    Page<Problem> hostList(String label, Pageable pageable);


    @Query(value = "SELECT * FROM tb_problem, tb_pl WHERE id = problemid AND labelid=? AND reply=0 ORDER BY reply DESC", nativeQuery = true)
    Page<Problem> waitList(String label, Pageable pageable);


    @Query(value = "SELECT * FROM tb_problem, tb_pl WHERE id = problemid AND labelid=?", nativeQuery = true)
    Page<Problem> getAll(String label, Pageable pageable);
}
