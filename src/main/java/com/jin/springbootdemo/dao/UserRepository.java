package com.jin.springbootdemo.dao;

import com.jin.springbootdemo.bean.User1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * jpa
 */
@Repository
public interface UserRepository  extends JpaRepository<User1,Long> {
    /**
     * 根据用户名查询用户信息
     * jpa规范: httFps://www.ibm.com/developerworks/cn/opensource/os-cn-spring-jpa/websocket.html
     * @param username 用户名
     * @return 查询结果
     */
    List<User1> findAllByUsername(String username);
}
