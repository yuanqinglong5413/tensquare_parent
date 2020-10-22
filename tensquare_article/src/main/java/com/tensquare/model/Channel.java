package com.tensquare.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_channel")
public class Channel {

    @Id
    private String id;
    private String name;
    private String state;

    public String getId() {
        return id;
    }

    public Channel setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Channel setName(String name) {
        this.name = name;
        return this;
    }

    public String getState() {
        return state;
    }

    public Channel setState(String state) {
        this.state = state;
        return this;
    }

    public Channel() {
    }
    public Channel(String id,String name,String state){
        this.id = id;
        this.name = name;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
