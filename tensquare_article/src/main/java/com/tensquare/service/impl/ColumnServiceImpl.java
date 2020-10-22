package com.tensquare.service.impl;

import com.tensquare.model.Channel;
import com.tensquare.model.Column;
import com.tensquare.repository.ColumnRepository;
import com.tensquare.service.ChannelService;
import com.tensquare.service.ColumnService;
import com.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ColumnServiceImpl implements ColumnService {

    @Autowired
    ColumnRepository columnRepository;

    @Autowired
    IdWorker idWorker;


    @Override
    public List<Column> findAll() {
        return columnRepository.findAll();
    }

    @Override
    public Column findById(String columnId) {
        return columnRepository.findById(columnId).get();
    }

    @Override
    public void save(Column column) {
        column.setId(idWorker.nextId()+"");
        columnRepository.save(column);
    }

    @Override
    public void update(Column column) {
        columnRepository.save(column);
    }

    @Override
    public void deleteById(String columnId) {
        columnRepository.deleteById(columnId);
    }

    @Override
    public List<Column> search(Column column) {
        List<Column> list = columnRepository.findAll(new Specification<Column>() {

            @Nullable
            @Override
            public Predicate toPredicate(Root<Column> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                List <Predicate> lists = new ArrayList<>();
                if (column != null && !"".equals(column.getName())){
                    predicate = criteriaBuilder.like(root.get("title").as(String.class), "%" + column.getName() + "%");
                    lists.add(predicate);
                }
                if (column != null && !"".equals(column.getId())){

                    predicate = criteriaBuilder.like(root.get("id").as(String.class), "%" + column.getId() + "%");
                    lists.add(predicate);
                }
                Predicate[] array = lists.toArray(new Predicate[lists.size()]);
                return criteriaBuilder.and(array);
            }
        });
        return list;
    }

    @Override
    public Page<Column> searchPage(Column column, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo-1, size);
        Page<Column> page1 = columnRepository.findAll(new Specification <Column>() {
            Predicate predicate = null;
            @Nullable
            @Override
            public Predicate toPredicate(Root <Column> root, CriteriaQuery <?> query, CriteriaBuilder criteriaBuilder) {
                List <Predicate> listl = new ArrayList <>();
                if (column != null && !"".equals(column.getName())){
                    predicate = criteriaBuilder.like(root.get("title").as(String.class), "%" + column.getName() + "%");
                    listl.add(predicate);
                }
                if (column != null && !"".equals(column.getId())){

                    predicate = criteriaBuilder.like(root.get("id").as(String.class), "%" + column.getId() + "%");
                    listl.add(predicate);
                }
                Predicate[] array = listl.toArray(new Predicate[listl.size()]);
                return criteriaBuilder.and(array);
            }
        },pageable);
        return page1;
    }
}
