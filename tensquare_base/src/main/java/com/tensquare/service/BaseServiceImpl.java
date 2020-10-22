package com.tensquare.service;

import com.tensquare.model.Label;
import com.tensquare.repository.BaseRepository;
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
public class BaseServiceImpl implements BaseService{

    @Autowired
    BaseRepository baseRepository;

    @Autowired
    IdWorker idWorker;

    @Override
    public List<Label> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public Label findById(String labelId) {
        Label label = baseRepository.findById(labelId).get();
        return label;
    }

    @Override
    public void save(Label label) {
        label.setId(idWorker.nextId()+"");
        baseRepository.save(label);
    }

    @Override
    public void update(Label label) {
        //更新也是save
        baseRepository.save(label);
    }

    @Override
    public void deleteById(String labelId) {
        baseRepository.deleteById(labelId);
    }

    @Override
    public List<Label> search(Label label) {
        //select * from tb_label where id =
        //select * from tb_label
        //select * from tb_label where labelname like '%java%'
        //select * from tb_label where labelname = '%java%' (and id = '')
        List<Label> list = baseRepository.findAll(new Specification<Label>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                List<Predicate> list = new ArrayList();
                if (label != null && !"".equals(label.getLabelname())) {
                    //where like labelname '%java%'
                    predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if (label != null && !"".equals(label.getId())){
                    //and id = 1
                    predicate = criteriaBuilder.equal(root.get("id").as(String.class),label.getId());
                    list.add(predicate);
                }
                Predicate[] predicateArray = list.toArray(new Predicate[list.size()]);
                return criteriaBuilder.and(predicateArray);
            }
        });
        return list;
    }

    ///模糊查询+分页
    @Override
    public Page<Label> searchPage(Label label, Integer pageNo, Integer size) {
        //设置分页数据
        Pageable pageable = PageRequest.of(pageNo-1,size);
        Page<Label> page = baseRepository.findAll(new Specification<Label>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                List<Predicate> list = new ArrayList();
                if (label != null && !"".equals(label.getLabelname())) {
                    //where like labelname '%java%'
                    predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if (label != null && !"".equals(label.getId())){
                    //and id = 1
                    predicate = criteriaBuilder.equal(root.get("id").as(String.class),label.getId());
                    list.add(predicate);
                }
                Predicate[] predicateArray = list.toArray(new Predicate[list.size()]);
                return criteriaBuilder.and(predicateArray);
            }
        },pageable);
        return page;
    }
}
