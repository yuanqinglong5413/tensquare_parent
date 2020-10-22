package com.tensquare.util;

import java.util.List;

public class PageResult<T> {

    private long total;   // 总条数
    private List<T>  rows;   //所有结果

    public long getTotal() {
        return total;
    }

    public PageResult<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public List<T> getRows() {
        return rows;
    }

    public PageResult<T> setRows(List<T> rows) {
        this.rows = rows;
        return this;
    }

    public PageResult() {
    }

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
