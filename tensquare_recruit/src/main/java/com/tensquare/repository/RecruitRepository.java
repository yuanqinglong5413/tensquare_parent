package com.tensquare.repository;

import com.tensquare.model.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RecruitRepository extends JpaRepository<Recruit,String>, JpaSpecificationExecutor<Recruit> {
    Object findTop2ByStateOrderByCreatetimeDesc(String state);

    Object findTop4ByStateNotOrderByCreatetimeDesc(String state);
}
