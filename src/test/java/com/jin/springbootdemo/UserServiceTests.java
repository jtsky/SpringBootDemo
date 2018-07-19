package com.jin.springbootdemo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jin.springbootdemo.bean.User;
import com.jin.springbootdemo.bean.User1;
import com.jin.springbootdemo.bean.User2;
import com.jin.springbootdemo.bean.User3;
import com.jin.springbootdemo.dao.UserRepository;
import com.jin.springbootdemo.dao.mapper.UserMapper;
import com.jin.springbootdemo.dao.mapper.UserMapper2;
import com.jin.springbootdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootdemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTests {
    private static final Logger log = LoggerFactory.getLogger(UserServiceTests.class);
    @Autowired
    UserService mUserService;

    @Autowired
    UserRepository mUserRepository;

    @Autowired
    UserMapper mUserMapper;

    @Autowired
    UserMapper2 mUserMapper2;

    @Test
    public void contextLoads() {
    }

    /**
     * jdbcTemplate
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
//        User user = new User();
//        user.setUsername("jint");
//        user.setPassword("12345678");
//        int isSuccess = mUserService.addUser(user);
//        log.info("[添加用户成功]\n");


//        User user1 = mUserService.getUser(1);
//        log.info("[查询用户成功]==>" + user1.toString());
        User user = new User();
        user.setUsername("jintai");
        user.setPassword("1234");
        mUserService.editUser(1, user);
        log.info("[修改用户成功]");

        User user1 = mUserService.getUser(1);
        log.info("[查询用户成功]==>" + user1.toString());
    }

    /**
     * jpa
     */
    @Test
    public void test2() {
//        User1 user = new User1();
//        user.setEmail("704167880@qq.com");
//        user.setPassword("12345678");
//        user.setUsername("jint");
//        User1 user1 = mUserRepository.save(user);
        List<User1> users = mUserRepository.findAllByUsername("jint");
        log.info("[添加成功] - [{}]", users);
    }


    /**
     * mybatis
     */
    @Test
    public void test3() {
        final int row1 = mUserMapper.insert(new User2("u1", "p1"));
        log.info("[添加结果] - [{}]", row1);
        final int row2 = mUserMapper.insert(new User2("u2", "p2"));
        log.info("[添加结果] - [{}]", row2);
        final int row3 = mUserMapper.insert(new User2("u1", "p3"));
        log.info("[添加结果] - [{}]", row3);
        final List<User2> u1 = mUserMapper.findByUsername("u1");
        log.info("[根据用户名查询] - [{}]", u1);
    }


    /**
     * mybatis 通用mapper
     */
    @Test
    public void test4() {
        final User3 user1 = new User3("u1", "p1");
        final User3 user2 = new User3("u1", "p2");
        final User3 user3 = new User3("u3", "p3");
        mUserMapper2.insertSelective(user1);
        log.info("[user1回写主键] - [{}]", user1.getId());
        mUserMapper2.insertSelective(user2);
        log.info("[user2回写主键] - [{}]", user2.getId());
        mUserMapper2.insertSelective(user3);
        log.info("[user3回写主键] - [{}]", user3.getId());
        final int count = mUserMapper2.countByUsername("u1");
        log.info("[调用自己写的SQL] - [{}]", count);

        // TODO 模拟分页
        mUserMapper2.insertSelective(new User3("u1", "p1"));
        mUserMapper2.insertSelective(new User3("u1", "p1"));
        mUserMapper2.insertSelective(new User3("u1", "p1"));
        mUserMapper2.insertSelective(new User3("u1", "p1"));
        mUserMapper2.insertSelective(new User3("u1", "p1"));
        mUserMapper2.insertSelective(new User3("u1", "p1"));
        mUserMapper2.insertSelective(new User3("u1", "p1"));
        mUserMapper2.insertSelective(new User3("u1", "p1"));
        mUserMapper2.insertSelective(new User3("u1", "p1"));
        mUserMapper2.insertSelective(new User3("u1", "p1"));
        // TODO 分页 + 排序 this.userMapper.selectAll() 这一句就是我们需要写的查询，有了这两款插件无缝切换各种数据库
        final PageInfo<Object> pageInfo = PageHelper.startPage(1, 10).setOrderBy("id desc").doSelectPageInfo(() -> this.mUserMapper2.selectAll());
        log.info("[lambda写法] - [{}]", pageInfo.toString());

        PageHelper.startPage(1, 10).setOrderBy("id desc");
        final PageInfo<User3> userPageInfo = new PageInfo<>(this.mUserMapper2.selectAll());
        log.info("[普通写法] - [{}]", userPageInfo);
    }


}
