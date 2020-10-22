package com.tensquare.service.Impl;

import com.tensquare.model.Enterprise;
import com.tensquare.repository.Enpository;
import com.tensquare.service.EnterpriseService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    @Autowired
    Enpository enpository;

    @Autowired
    IdWorker idWorker;

    @Override
    public List<Enterprise> findAll() {
        return enpository.findAll();
    }

    @Override
    public Enterprise findById(String enterpriseId) {
        Enterprise enterprise1 = enpository.findById(enterpriseId).get();
        return enterprise1;
    }

    @Override
    public void add(Enterprise enterprise) {
        enterprise.setId(idWorker.nextId()+"");
        enpository.save(enterprise);
    }

    @Override
    public void update(Enterprise enterprise) {
        enpository.save(enterprise);
    }

    @Override
    public void delete(String enterpriseId) {
        enpository.deleteById(enterpriseId);
    }

    @Override
    public List <Enterprise> search(Enterprise enterprise) {

        List<Enterprise> list = enpository.findAll(new Specification <Enterprise>() {

            @Nullable
            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                List <Predicate> lists = new ArrayList <>();
                if (enterprise != null && !"".equals(enterprise.getName())){
                    predicate = criteriaBuilder.like(root.get("name").as(String.class), "%" + enterprise.getName() + "%");
                    lists.add(predicate);
                }
                if (enterprise != null && !"".equals(enterprise.getId())){

                    predicate = criteriaBuilder.like(root.get("id").as(String.class), "%" + enterprise.getId() + "%");
                    lists.add(predicate);
                }
                Predicate[] array = lists.toArray(new Predicate[lists.size()]);
                return criteriaBuilder.and(array);
            }
        });
        return list;
    }

    @Override
    public Page searchPage(Enterprise enterprise, Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page-1, size);
        Page<Enterprise> page1 = enpository.findAll(new Specification <Enterprise>() {
            Predicate predicate = null;
            @Nullable
            @Override
            public Predicate toPredicate(Root <Enterprise> root, CriteriaQuery <?> query, CriteriaBuilder criteriaBuilder) {
                List <Predicate> listl = new ArrayList <>();
                if (enterprise != null && !"".equals(enterprise.getName())){
                    predicate = criteriaBuilder.like(root.get("name").as(String.class), "%" + enterprise.getName() + "%");
                    listl.add(predicate);
                }
                if (enterprise != null && !"".equals(enterprise.getId())){

                    predicate = criteriaBuilder.like(root.get("id").as(String.class), "%" + enterprise.getId() + "%");
                    listl.add(predicate);
                }
                Predicate[] array = listl.toArray(new Predicate[listl.size()]);
                return criteriaBuilder.and(array);
            }
        },pageable);
        return page1;
    }

    @Override
    public List <Enterprise> findByIshot(String ishot) {
       List<Enterprise> enterprise = enpository.findByIshot(ishot);
        return enterprise;
    }


}
