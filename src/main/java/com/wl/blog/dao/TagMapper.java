package com.wl.blog.dao;

import com.wl.blog.pojo.BlogTag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper {
    //查询所有的tag
    public List<BlogTag> findAllTags();

    //根据CategoryId查询id
    public List<BlogTag> findTagsByCategoryId(int CategoryId);
}
