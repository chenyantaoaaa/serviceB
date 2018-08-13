package com.springcloud.splitTable.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.splitTable.mapper.UserMapper;
import com.springcloud.splitTable.pojo.User;
import io.swagger.annotations.ApiOperation;

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
    @CrossOrigin
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

    @RequestMapping(value = "createTmpTable",method = RequestMethod.GET)
    public void createTmpTable(){
        userMapper.createTmpTable();
    }

    @RequestMapping(value = "testUpdateCost",method = RequestMethod.GET)
    public void testUpdateCost(){
        User user = new User();
        user.setUserid("1");
        user.setName("chen");
        user.setSex(1);
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            user.setAge(i);
            userMapper.testUpdateCost(user);
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2-time1);
    }



    public static void main(String[] args) {
        long useridNum =Long.valueOf (System.currentTimeMillis()+""+Math.round(Math.random()*10000));
        System.out.println(useridNum);
        int num = (int)useridNum%2;
        System.out.println(num);
    }
}
