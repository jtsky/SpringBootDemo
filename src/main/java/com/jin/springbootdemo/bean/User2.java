package com.jin.springbootdemo.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * mybatis bean
 */
@Data
public class User2 implements Serializable {
    private Long id;
    private String username;
    private String password;

    public User2(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
