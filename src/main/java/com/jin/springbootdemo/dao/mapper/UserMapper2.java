package com.jin.springbootdemo.dao.mapper;

import com.jin.springbootdemo.bean.User3;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * 通用mapper
 *
 *
 *
 * @author jint
 */
@Mapper
public interface UserMapper2  extends BaseMapper<User3> {

    /**
     * 根据用户名统计（TODO 假设它是一个很复杂的SQL）
     *
     * @param username 用户名
     * @return 统计结果
     */
    int countByUsername(String username);
}
