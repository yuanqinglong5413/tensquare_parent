package com.tensquare.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    @Id
    private String id; //ID
    private String mobile; //手机号
    private String password; //密码
    private String nickname; //昵称
    private String sex; //性别
    private java.util.Date birthday; //生日
    private String avatar; //头像
    private String email; //E-Mail
    private java.util.Date regdate; //注册日期
    private java.util.Date updatedate; //修改日期
    private java.util.Date lastdate; //最后登陆日期
    private Long online; //在线时长(分钟)
    private String interest; //兴趣
    private String personality; //个性
    private Integer fanscount; //粉丝数
    private Integer followcount; //关注数

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public User setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public User setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public User setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public User setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public User setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getRegdate() {
        return regdate;
    }

    public User setRegdate(Date regdate) {
        this.regdate = regdate;
        return this;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public User setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
        return this;
    }

    public Date getLastdate() {
        return lastdate;
    }

    public User setLastdate(Date lastdate) {
        this.lastdate = lastdate;
        return this;
    }

    public Long getOnline() {
        return online;
    }

    public User setOnline(Long online) {
        this.online = online;
        return this;
    }

    public String getInterest() {
        return interest;
    }

    public User setInterest(String interest) {
        this.interest = interest;
        return this;
    }

    public String getPersonality() {
        return personality;
    }

    public User setPersonality(String personality) {
        this.personality = personality;
        return this;
    }

    public Integer getFanscount() {
        return fanscount;
    }

    public User setFanscount(Integer fanscount) {
        this.fanscount = fanscount;
        return this;
    }

    public Integer getFollowcount() {
        return followcount;
    }

    public User setFollowcount(Integer followcount) {
        this.followcount = followcount;
        return this;
    }

    public User() {
    }
    public User(String id, String mobile,String password, String nickname, String sex,java.util.Date birthday,String avatar,String email,java.util.Date regdate,java.util.Date updatedate,java.util.Date lastdate,Long online,String interest,String personality,Integer fanscount,Integer followcount ) {
        this.id = id;
        this.mobile = mobile;
        this.password = password;
        this.nickname = nickname;
        this.sex = sex;
        this.birthday = birthday
        this.avatar = avatar;
        this.email = email;
        this.regdate = regdate;
        this.updatedate = updatedate;
        this.lastdate = lastdate;
        this.online = online;
        this.interest = interest;
        this.personality = personality;
        this.fanscount = fanscount;
        this.followcount = followcount;
    }
}
