package com.wl.blog.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface StatusMapper {
    //根据id查询文章的状态
    public String findStatusById(int StatusId);
}
