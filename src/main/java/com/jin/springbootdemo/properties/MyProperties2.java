package com.jin.springbootdemo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 读取自定义yml 有坑 参考:https://tomoya92.github.io/2017/11/10/spring-boot-self-yml/
 */
@Component
@PropertySource("classpath:my2.yml")
@ConfigurationProperties(prefix = "my2")
public class MyProperties2 {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
