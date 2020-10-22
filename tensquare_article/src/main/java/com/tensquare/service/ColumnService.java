package com.tensquare.service;

import com.tensquare.model.Column;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ColumnService {
    List<Column> findAll();

    Column findById(String columnId);

    void save(Column column);

    void update(Column column);

    void deleteById(String columnId);

    List<Column> search(Column column);

    Page<Column> searchPage(Column column, Integer pageNo, Integer size);
}
