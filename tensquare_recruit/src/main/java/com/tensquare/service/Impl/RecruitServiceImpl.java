package com.tensquare.service.Impl;

import com.tensquare.model.Recruit;
import com.tensquare.repository.RecruitRepository;
import com.tensquare.service.RecruitService;
import com.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RecruitServiceImpl implements RecruitService {

    @Autowired
    RecruitRepository recruitRepository;

    @Autowired
    IdWorker idWorker;

    @Override
    public List<Recruit> findAll() {
        return recruitRepository.findAll();
    }

    @Override
    public Recruit findById(String recruitId) {
        Recruit recruit = recruitRepository.findById(recruitId).get();
        return recruit;
    }

    @Override
    public void save(Recruit recruit) {

        recruit.setId(idWorker.nextId()+"");
        recruitRepository.save(recruit);
    }

    @Override
    public void update(Recruit recruit) {

        recruitRepository.save(recruit);
    }

    @Override
    public void deleteById(String recruitId) {

        recruitRepository.deleteById(recruitId);
    }

    @Override
    public List<Recruit> search(Recruit recruit) {
        List<Recruit> list = recruitRepository.findAll(new Specification<Recruit>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                List<Predicate> list = new ArrayList();
                if (recruit != null && !"".equals(recruit.getJobname())) {
                    //where like labelname '%java%'
                    predicate = criteriaBuilder.like(root.get("jobname").as(String.class), "%" + recruit.getJobname() + "%");
                    list.add(predicate);
                }
                if (recruit != null && !"".equals(recruit.getId())){
                    //and id = 1
                    predicate = criteriaBuilder.equal(root.get("id").as(String.class),recruit.getId());
                    list.add(predicate);
                }
                Predicate[] predicateArray = list.toArray(new Predicate[list.size()]);
                return criteriaBuilder.and(predicateArray);
            }
        });
        return list;
    }

    @Override
    public Page<Recruit> searchPage(Recruit recruit, Integer pageNo, Integer size) {
        //设置分页数据
        Pageable pageable = PageRequest.of(pageNo-1,size);
        Page<Recruit> page = recruitRepository.findAll(new Specification<Recruit>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                List<Predicate> list = new ArrayList();
                if (recruit != null && !"".equals(recruit.getJobname())) {
                    //where like labelname '%java%'
                    predicate = criteriaBuilder.like(root.get("jobname").as(String.class), "%" + recruit.getJobname() + "%");
                    list.add(predicate);
                }
                if (recruit != null && !"".equals(recruit.getId())){
                    //and id = 1
                    predicate = criteriaBuilder.equal(root.get("id").as(String.class),recruit.getId());
                    list.add(predicate);
                }
                Predicate[] predicateArray = list.toArray(new Predicate[list.size()]);
                return criteriaBuilder.and(predicateArray);
            }
        },pageable);
        return page;
    }

    @Override
    public Object findByRecom(String state) {
        return recruitRepository.findTop2ByStateOrderByCreatetimeDesc(state);
    }

    @Override
    public Object findByNewRecom(String state) {
        return recruitRepository.findTop4ByStateNotOrderByCreatetimeDesc(state);
    }
}
