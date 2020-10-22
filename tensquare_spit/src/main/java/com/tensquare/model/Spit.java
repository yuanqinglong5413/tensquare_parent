package com.tensquare.model;

import java.util.Date;

public class Spit{

    private String _id;
    private String content;
    private String publishtime;
    private String userid;
    private String nickname;
    private Integer visits;
    private Integer thumbup;
    private Integer share;
    private Integer comment;
    private String state;
    private String parentid;

    public String get_id() {
        return _id;
    }

    public Spit set_id(String _id) {
        this._id = _id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Spit setContent(String content) {
        this.content = content;
        return this;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public Spit setPublishtime(String publishtime) {
        this.publishtime = publishtime;
        return this;
    }

    public String getUserid() {
        return userid;
    }

    public Spit setUserid(String userid) {
        this.userid = userid;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public Spit setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Integer getVisits() {
        return visits;
    }

    public Spit setVisits(Integer visits) {
        this.visits = visits;
        return this;
    }

    public Integer getThumbup() {
        return thumbup;
    }

    public Spit setThumbup(Integer thumbup) {
        this.thumbup = thumbup;
        return this;
    }

    public Integer getShare() {
        return share;
    }

    public Spit setShare(Integer share) {
        this.share = share;
        return this;
    }

    public Integer getComment() {
        return comment;
    }

    public Spit setComment(Integer comment) {
        this.comment = comment;
        return this;
    }

    public String getState() {
        return state;
    }

    public Spit setState(String state) {
        this.state = state;
        return this;
    }

    public String getParentid() {
        return parentid;
    }

    public Spit setParentid(String parentid) {
        this.parentid = parentid;
        return this;
    }

    public Spit() {
    }

    public Spit(String _id, String content, String publishtime, String userid, String nickname, Integer visits, Integer thumbup, Integer share, Integer comment, String state, String parentid) {
        this._id = _id;
        this.content = content;
        this.publishtime = publishtime;
        this.userid = userid;
        this.nickname = nickname;
        this.visits = visits;
        this.thumbup = thumbup;
        this.share = share;
        this.comment = comment;
        this.state = state;
        this.parentid = parentid;
    }
}
