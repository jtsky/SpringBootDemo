package com.jin.springbootdemo.controller;

import com.jin.springbootdemo.bean.Author;
import com.jin.springbootdemo.properties.MyProperties;
import com.jin.springbootdemo.properties.MyProperties2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/")
@Api(tags = "1.1", description = "测试", value = "测试")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private final MyProperties myProperties;

    private final MyProperties2 myProperties2;

    @Autowired
    public TestController(MyProperties myProperties, MyProperties2 myProperties2) {
        this.myProperties = myProperties;
        this.myProperties2 = myProperties2;
    }

    @GetMapping("hello")
    @ApiOperation(value = "测试")
    public Map hello() {
        Map<String, String> map = new HashMap<>();
        map.put("name", myProperties.getName());
        map.put("age", "" + myProperties.getAge());
        map.put("name2", myProperties2.getName());
        map.put("age2", "" + myProperties2.getAge());
        return map;
    }



    @GetMapping("index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView();
        // 设置跳转的视图 默认映射到 src/main/resources/templates/{viewName}.html
        view.setViewName("index");
        // 设置属性
        view.addObject("title", "我的第一个WEB页面");
        view.addObject("desc", "欢迎进入battcn-web 系统");
        Author author = new Author();
        author.setAge(myProperties.getAge());
        author.setEmail("1837307557@qq.com");
        author.setName(myProperties.getName());
        view.addObject("author", author);

        return view;
    }

}
