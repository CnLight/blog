package com.wl.blog.dao;

import com.wl.blog.pojo.BlogUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    //查询所有用户
    @Results({
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "user_email", column = "user_email"),
            @Result(property = "user_name", column = "user_name"),
            @Result(property = "user_pwd", column = "user_pwd")
    })
    @Select("select * from blog_user")
    public List<BlogUser> findAllUser();

    //根据id查询所有用户
    @Results({
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "user_email", column = "user_email"),
            @Result(property = "user_name", column = "user_name"),
            @Result(property = "user_pwd", column = "user_pwd")
    })
    @Select("select * from blog_user where user_id=#{id}")
    public BlogUser findAllUserById(int id);

    //根据id查询所有用户
    @Results({
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "user_email", column = "user_email"),
            @Result(property = "user_name", column = "user_name"),
            @Result(property = "user_pwd", column = "user_pwd")
    })
    @Select("select * from blog_user where user_pwd=#{user_pwd} and user_name=#{user_name}")
    public BlogUser findAllUserByIdAndPwd(Map map);

    //删除
    @Delete("delete  from blog_user where user_id =#{id}")
    public void deleteUser(int id);

    //更新
    @Update("update blog_user set user_email=#{user_email} ,user_name=#{user_name}" +
            ",user_pwd=#{user_pwd} where user_id=#{user_id}")
    public void updateUser(BlogUser blogUser);

    //添加用户
    @Insert("insert into blog_user(user_email,user_name,user_pwd) values (#{user_email},#{user_name},#{user_pwd})")
    public void addUser(BlogUser blogUser);

    //查询用户的人数
    @Select("select count(*) from blog_user")
    public int findNumFromUser();


}
