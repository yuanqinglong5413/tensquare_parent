package com.tensquare.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_problem")
public class Problem implements Serializable {

    @Id
    private String id;
    private String title;
    private String content;
    private Date createtime;
    private Date updatetime;
    private String userid;
    private String nickname;
    private Long visits;
    private Long thumbup;
    private Long  reply;
    private String solve;
    private String replyname;
    private Date replytime;

    public String getId() {
        return id;
    }


    public Date getCreatetime() {
        return createtime;
    }

    public Problem setCreatetime(Date createtime) {
        this.createtime = createtime;
        return this;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public Problem setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
        return this;
    }

    public Date getReplytime() {
        return replytime;
    }

    public Problem setReplytime(Date replytime) {
        this.replytime = replytime;
        return this;
    }

    public Problem setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Problem setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Problem setContent(String content) {
        this.content = content;
        return this;
    }

    public String getUserid() {
        return userid;
    }

    public Problem setUserid(String userid) {
        this.userid = userid;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public Problem setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Long getVisits() {
        return visits;
    }

    public Problem setVisits(Long visits) {
        this.visits = visits;
        return this;
    }

    public Long getThumbup() {
        return thumbup;
    }

    public Problem setThumbup(Long thumbup) {
        this.thumbup = thumbup;
        return this;
    }

    public Long getReply() {
        return reply;
    }

    public Problem setReply(Long reply) {
        this.reply = reply;
        return this;
    }

    public String getSolve() {
        return solve;
    }

    public Problem setSolve(String solve) {
        this.solve = solve;
        return this;
    }

    public String getReplyname() {
        return replyname;
    }

    public Problem setReplyname(String replyname) {
        this.replyname = replyname;
        return this;
    }

    public Problem() {
    }
    public Problem(String id, String title, String content, Date createtime, Date updatetime, String userid, String nickname, Long visits, Long thumbup, Long reply, String solve, String replyname, Date replytime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.userid = userid;
        this.nickname = nickname;
        this.visits = visits;
        this.thumbup = thumbup;
        this.reply = reply;
        this.solve = solve;
        this.replyname = replyname;
        this.replytime = replytime;
    }
    @Override
    public String toString() {
        return "Problem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", userid='" + userid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", visits=" + visits +
                ", thumbup=" + thumbup +
                ", reply=" + reply +
                ", solve='" + solve + '\'' +
                ", replyname='" + replyname + '\'' +
                ", replytime=" + replytime +
                '}';
    }
}
