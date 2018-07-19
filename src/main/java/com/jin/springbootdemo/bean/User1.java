package com.jin.springbootdemo.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * jpa
 */
@Entity(name = "t_user1")
@Data
public class User1 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    /**
     * TODO 忽略该字段的映射
     */
    @Transient
    private String email;
}
