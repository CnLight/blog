package com.wl.blog.service.admin;

import com.wl.blog.dao.CategoryMapper;
import com.wl.blog.pojo.ArticleCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    public List<ArticleCategory> findAllArticleCategories() {
        return categoryMapper.findAllArticleCategories();
    }
}
