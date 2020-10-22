package com.tensquare.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="tb_column")
public class Column {

    @Id
    private String id;
    private String name;
    private String summary;
    private String userid;
    private Date createtime;
    private Date checktime;
    private String state;

    public String getId() {
        return id;
    }

    public Column setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Column setName(String name) {
        this.name = name;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Column setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getUserid() {
        return userid;
    }

    public Column setUserid(String userid) {
        this.userid = userid;
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public Column setCreatetime(Date createtime) {
        this.createtime = createtime;
        return this;
    }

    public Date getChecktime() {
        return checktime;
    }

    public Column setChecktime(Date checktime) {
        this.checktime = checktime;
        return this;
    }

    public String getState() {
        return state;
    }

    public Column setState(String state) {
        this.state = state;
        return this;
    }

    public Column() {
    }

    public Column(String id,String name,String summary,String userid,Date createtime,Date checktime,String state) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.userid = userid;
        this.createtime = createtime;
        this.checktime = checktime;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Column{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", userid='" + userid + '\'' +
                ", createtime=" + createtime +
                ", checktime=" + checktime +
                ", state='" + state + '\'' +
                '}';
    }
}
