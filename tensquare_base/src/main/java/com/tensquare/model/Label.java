package com.tensquare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_label")
public class Label {

    @Id
    private String id;
    //@Column(name = "label_name")
    private String labelname;
    private String state;
    private Integer count;
    private String recommend;

    public String getId() {
        return id;
    }

    public Label setId(String id) {
        this.id = id;
        return this;
    }

    public String getLabelname() {
        return labelname;
    }

    public Label setLabelname(String labelname) {
        this.labelname = labelname;
        return this;
    }

    public String getState() {
        return state;
    }

    public Label setState(String state) {
        this.state = state;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public Label setCount(Integer count) {
        this.count = count;
        return this;
    }

    public String getRecommend() {
        return recommend;
    }

    public Label setRecommend(String recommend) {
        this.recommend = recommend;
        return this;
    }
}
