package com.tensquare.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="tb_admin")
public class Admin implements Serializable {

    @Id
    private String id;//ID
    private String loginname;//登陆名称
    private String password;//密码
    private String state;//状态

    public String getId() {
        return id;
    }

    public Admin setId(String id) {
        this.id = id;
        return this;
    }

    public String getLoginname() {
        return loginname;
    }

    public Admin setLoginname(String loginname) {
        this.loginname = loginname;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Admin setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getState() {
        return state;
    }

    public Admin setState(String state) {
        this.state = state;
        return this;
    }

    public Admin() {
    }
    public Admin(String id,String loginname,String password,String state) {
        this.id = id;
        this.loginname = loginname;
        this.password = password;
        this.state = state;
    }
}
