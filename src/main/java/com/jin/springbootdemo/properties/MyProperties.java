package com.jin.springbootdemo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义配置属性
 */
@Component
@ConfigurationProperties(prefix = "my1")
@Data
public class MyProperties {
    private String name;
    private int age;

}
