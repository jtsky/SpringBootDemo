package com.jin.springbootdemo.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {
    private String id;
    private String name;
}
