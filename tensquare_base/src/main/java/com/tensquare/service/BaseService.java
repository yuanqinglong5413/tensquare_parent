package com.tensquare.service;

import com.tensquare.model.Label;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService {

    List<Label> findAll();

    Label findById(String labelId);

    void save(Label label);

    void update(Label label);

    void deleteById(String labelId);

    List<Label> search(Label label);

    Page<Label> searchPage(Label label, Integer pageNo, Integer size);
}
