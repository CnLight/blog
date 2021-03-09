package com.wl.blog.service.admin;

import com.wl.blog.dao.UserMapper;
import com.wl.blog.pojo.BlogUser;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    //查询所有用户
    public List<BlogUser> findAllUser() {
        return userMapper.findAllUser();
    }

    //删除用户
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    //更新用户
    public void updateUser(BlogUser blogUser) {
        userMapper.updateUser(blogUser);
    }

    //根据用户Id查询用户
    public BlogUser findUserById(int id) {
        return userMapper.findAllUserById(id);
    }

    //根据user_name pwd查询用户
    public BlogUser findAllUserByIdAndPwd(Map map) {
        return userMapper.findAllUserByIdAndPwd(map);
    }

    //添加用户
    public void addUser(BlogUser blogUser) {
        userMapper.addUser(blogUser);
    }

    //用户的人数
    public int findNumFromUser() {
        return userMapper.findNumFromUser();

    }
}
