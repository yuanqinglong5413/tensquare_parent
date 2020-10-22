package com.tensquare.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="tb_reply")
public class Reply implements Serializable {

    @Id
    private String id;//编号
    private String problemid;//问题ID
    private String content;//回答内容
    private Date createtime;//创建日期
    private Date updatetime;//更新日期
    private String userid;//回答人ID
    private String nickname;//回答人昵称

    public Reply() {
    }

    public String getId() {
        return id;
    }

    public Reply setId(String id) {
        this.id = id;
        return this;
    }

    public String getProblemid() {
        return problemid;
    }

    public Reply setProblemid(String problemid) {
        this.problemid = problemid;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Reply setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public Reply setCreatetime(Date createtime) {
        this.createtime = createtime;
        return this;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public Reply setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
        return this;
    }

    public String getUserid() {
        return userid;
    }

    public Reply setUserid(String userid) {
        this.userid = userid;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public Reply setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Reply(String id,String problemid,String content,Date createtime,Date updatetime,String userid,String nickname) {

        this.id = id;
        this.problemid = id;
        this.content = content;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.userid = userid;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id='" + id + '\'' +
                ", problemid='" + problemid + '\'' +
                ", content='" + content + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", userid='" + userid + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
