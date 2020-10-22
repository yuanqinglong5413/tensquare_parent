package com.tensquare.service.impl;

import com.tensquare.model.Spit;
import com.tensquare.repository.SpitRepository;
import com.tensquare.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SpitServiceImpl implements SpitService {

    @Autowired
    SpitRepository spitRepository;

    @Override
    public List<Spit> findAll() {

        List<Spit> list = spitRepository.findAll();
        return list;
    }
    @Override
    public Spit findById(String id){
        return spitRepository.findById(id).get();
    }

    @Override
    public void save(Spit spit){
        spitRepository.save(spit);
    }

    @Override
    public void update(Spit spit){
        spitRepository.save(spit);
    }

    @Override
    public void deleteById(String id){
        spitRepository.deleteById(id);
    }

    @Override
    public Page<Spit> findByParentid(String parentid, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Spit> findByParentid(String parentid, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return spitRepository.findByParentid(parentid, pageable);
    }


}
