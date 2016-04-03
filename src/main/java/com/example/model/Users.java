package com.example.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Igor
 */
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID",nullable = false)
    private int id;

    @Column(name = "USER_NAME",nullable = false,length = 20)
    private String name;

    public Users(){}
    public Users(String userName){
        this.name=userName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
