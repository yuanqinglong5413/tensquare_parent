package com.tensquare.service;

import com.tensquare.model.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SpitService {
    public List<Spit> findAll();

    public Spit findById(String id);

    public void save(Spit spit);

    public void update(Spit spit);

    public void deleteById(String id);

    public Page<Spit> findByParentid(String parentid, Pageable pageable);

    Page<Spit> findByParentid(String parentid, int page, int size);
}
