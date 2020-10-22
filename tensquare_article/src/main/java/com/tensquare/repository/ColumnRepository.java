package com.tensquare.repository;

import com.tensquare.model.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ColumnRepository extends JpaRepository<Column,String>, JpaSpecificationExecutor<Column> {

}
