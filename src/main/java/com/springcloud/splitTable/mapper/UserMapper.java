package com.springcloud.splitTable.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springcloud.splitTable.pojo.User;

/**
 * Created by yantao.chen on 2018-07-05.
 */
@Component
public interface UserMapper {
    List<User> selectAll();

    int addUser(User user);

    User getUser(User user);

    void createTmpTable();

    int testUpdateCost(User user);
}
