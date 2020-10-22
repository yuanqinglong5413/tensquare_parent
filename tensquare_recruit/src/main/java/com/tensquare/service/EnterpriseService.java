package com.tensquare.service;

import com.tensquare.model.Enterprise;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EnterpriseService {


    List<Enterprise> findAll();

    Enterprise findById(String enterpriseId);

    void add(Enterprise enterprise);

    void update(Enterprise enterprise);

    void delete(String enterpriseId);

    List<Enterprise> search(Enterprise enterprise);

    Page searchPage(Enterprise enterprise, Integer page, Integer size);


    List<Enterprise> findByIshot(String ishot);
}
