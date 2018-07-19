package com.jin.springbootdemo;

import com.battcn.swagger.annotation.EnableSwagger2Doc;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//扫描指定文件下的mapper文件
//@MapperScan("com.jin.springbootdemo.dao.mapper")
//注意此处不要引错包
@MapperScan("com.jin.springbootdemo.dao.mapper")
@EnableSwagger2Doc
public class SpringbootdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoApplication.class, args);
    }


    /**
     * 读取自定义yml 有坑 参考:https://tomoya92.github.io/2017/11/10/spring-boot-self-yml/
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("my2.yml"));
        configurer.setProperties(yaml.getObject());
        return configurer;
    }
}
