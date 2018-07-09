package com.springcloud.splitTable.controllers;

import com.springcloud.splitTable.mapper.UserMapper;
import com.springcloud.splitTable.pojo.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yantao.chen on 2018-07-05.
 */
@RestController
@RequestMapping("split")
public class SplitDatabaseController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value ="getAllUsers",method = RequestMethod.GET)
    @ApiOperation(value="获取所有用户信息", notes="获取所有用户信息")
    public List<User> getAllUsers(){
        return userMapper.selectAll();
    }

    @RequestMapping(value = "addUser",method = RequestMethod.GET)
    public int addUser(int num){
        User user = new User();
        long useridNum =Long.valueOf (System.currentTimeMillis()+""+Math.round(Math.random()*10000));
        String userid = String.valueOf(useridNum);
        user.setUserid(userid);
        user.setAge(20);
        user.setName("name"+num);
        int sex =(int) Math.random();
        user.setSex(sex);
        user.setTableNum("user"+String.valueOf(useridNum%2));
        System.out.println(123);
        return userMapper.addUser(user);
    }

    @RequestMapping(value = "getUser",method = RequestMethod.GET)
    public User addUser(String id){
        User user = new User();
        user.setUserid(id);
        long num = Long.valueOf(id);
        user.setTableNum("user"+String.valueOf(num%2));
        return userMapper.getUser(user);
    }



    public static void main(String[] args) {
        long useridNum =Long.valueOf (System.currentTimeMillis()+""+Math.round(Math.random()*10000));
        System.out.println(useridNum);
        int num = (int)useridNum%2;
        System.out.println(num);
    }
}
