package com.jin.springbootdemo.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * mybatis 通用mapper
 */
@Data
@Table(name = "t_user3")
public class User3 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    public User3(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
