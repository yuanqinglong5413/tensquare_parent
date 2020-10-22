package com.tensquare.service;

import com.tensquare.model.Recruit;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RecruitService {

    List<Recruit> findAll();

    Recruit findById(String recruitId);

    void save(Recruit recruit);

    void update(Recruit recruit);

    void deleteById(String recruitId);

    List<Recruit> search(Recruit recruit);

    Page<Recruit> searchPage(Recruit recruit, Integer pageNo, Integer size);

    Object findByRecom(String state);

    Object findByNewRecom(String state);
}
