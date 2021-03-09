package com.wl.blog.service.admin;

import com.wl.blog.dao.TagMapper;
import com.wl.blog.pojo.BlogTag;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagService {

    @Resource
    TagMapper tagMapper;

    //查询所有的tag
    public List<BlogTag> findAllTags() {
        return tagMapper.findAllTags();
    }

    //根据CategoryId查询id
    public List<BlogTag> findTagsByCategoryId(int CategoryId) {
        return tagMapper.findTagsByCategoryId(CategoryId);
    }
}
