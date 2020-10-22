package com.tensquare.model;

import javax.persistence.Id;
import java.util.Date;

public class Recruit {

    @Id
    private String id;
    private String jobname;
    private String salary;
    private String condition;
    private String education;
    private String type;
    private String address;
    private String eid;
    private Date createtime;
    private String state;
    private String url;
    private String label;
    private String content1;
    private String content2;

    public Recruit() {
    }

    public Recruit(String id, String jobname, String salary, String condition, String education, String type, String address, String eid, Date createtime, String state, String url, String label, String content1, String content2) {
        this.id = id;
        this.jobname = jobname;
        this.salary = salary;
        this.condition = condition;
        this.education = education;
        this.type = type;
        this.address = address;
        this.eid = eid;
        this.createtime = createtime;
        this.state = state;
        this.url = url;
        this.label = label;
        this.content1 = content1;
        this.content2 = content2;
    }

    public String getId() {
        return id;
    }

    public Recruit setId(String id) {
        this.id = id;
        return this;
    }

    public String getJobname() {
        return jobname;
    }

    public Recruit setJobname(String jobname) {
        this.jobname = jobname;
        return this;
    }

    public String getSalary() {
        return salary;
    }

    public Recruit setSalary(String salary) {
        this.salary = salary;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public Recruit setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public String getEducation() {
        return education;
    }

    public Recruit setEducation(String education) {
        this.education = education;
        return this;
    }

    public String getType() {
        return type;
    }

    public Recruit setType(String type) {
        this.type = type;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Recruit setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEid() {
        return eid;
    }

    public Recruit setEid(String eid) {
        this.eid = eid;
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public Recruit setCreatetime(Date createtime) {
        this.createtime = createtime;
        return this;
    }

    public String getState() {
        return state;
    }

    public Recruit setState(String state) {
        this.state = state;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Recruit setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public Recruit setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getContent1() {
        return content1;
    }

    public Recruit setContent1(String content1) {
        this.content1 = content1;
        return this;
    }

    public String getContent2() {
        return content2;
    }

    public Recruit setContent2(String content2) {
        this.content2 = content2;
        return this;
    }
}
