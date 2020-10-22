package com.tensquare.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="tb_article")
public class Article implements Serializable {

    @Id
    private String id;//ID
    private String columnid;//专栏ID
    private String userid;//用户ID
    private String title;//标题
    private String content;//文章正文
    private String image;//文章封面
    private java.util.Date createtime;//发表日期
    private java.util.Date updatetime;//修改日期
    private String ispublic;//是否公开
    private String istop;//是否置顶
    private Integer visits;//浏览量
    private Integer thumbup;//点赞数
    private Integer comment;//评论数
    private String state;//审核状态  0：未审核 1：已审核
    private String channelid;//所属频道
    private String url;//URL
    private String type;//类型

    public Article() {
    }

    public String getId() {
        return id;
    }

    public Article setId(String id) {
        this.id = id;
        return this;
    }

    public String getColumnid() {
        return columnid;
    }

    public Article setColumnid(String columnid) {
        this.columnid = columnid;
        return this;
    }

    public String getUserid() {
        return userid;
    }

    public Article setUserid(String userid) {
        this.userid = userid;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Article setContent(String content) {
        this.content = content;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Article setImage(String image) {
        this.image = image;
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public Article setCreatetime(Date createtime) {
        this.createtime = createtime;
        return this;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public Article setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
        return this;
    }

    public String getIspublic() {
        return ispublic;
    }

    public Article setIspublic(String ispublic) {
        this.ispublic = ispublic;
        return this;
    }

    public String getIstop() {
        return istop;
    }

    public Article setIstop(String istop) {
        this.istop = istop;
        return this;
    }

    public Integer getVisits() {
        return visits;
    }

    public Article setVisits(Integer visits) {
        this.visits = visits;
        return this;
    }

    public Integer getThumbup() {
        return thumbup;
    }

    public Article setThumbup(Integer thumbup) {
        this.thumbup = thumbup;
        return this;
    }

    public Integer getComment() {
        return comment;
    }

    public Article setComment(Integer comment) {
        this.comment = comment;
        return this;
    }

    public String getState() {
        return state;
    }

    public Article setState(String state) {
        this.state = state;
        return this;
    }

    public String getChannelid() {
        return channelid;
    }

    public Article setChannelid(String channelid) {
        this.channelid = channelid;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Article setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getType() {
        return type;
    }

    public Article setType(String type) {
        this.type = type;
        return this;
    }

    public Article(String id,String columnid,String userid,String title,String content,String image,Date createtime,Date updatetime,String ispublic,String istop,Integer visits,Integer thumbup,Integer comment,String state,String channelid,String url,String type) {
     this.id = id;
     this.columnid = columnid;
     this.userid = userid;
     this.title = title;
     this.content = content;
     this.image = image;
     this.createtime = createtime;
     this.updatetime = updatetime;
     this.ispublic = ispublic;
     this.istop = istop;
     this.visits = visits;
     this.thumbup =thumbup;
     this.comment = comment;
     this.state = state;
     this.channelid = channelid;
     this.url =url;
     this.type = type;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", columnid='" + columnid + '\'' +
                ", userid='" + userid + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", ispublic='" + ispublic + '\'' +
                ", istop='" + istop + '\'' +
                ", visits=" + visits +
                ", thumbup=" + thumbup +
                ", comment=" + comment +
                ", state='" + state + '\'' +
                ", channelid='" + channelid + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
