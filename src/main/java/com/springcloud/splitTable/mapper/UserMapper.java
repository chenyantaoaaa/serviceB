package com.springcloud.splitTable.mapper;

import com.springcloud.splitTable.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yantao.chen on 2018-07-05.
 */
@Component
public interface UserMapper {
    List<User> selectAll();

    int addUser(User user);

    User getUser(User user);
}