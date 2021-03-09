package com.wl.blog.dao;

import com.wl.blog.pojo.ArticleCategory;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CategoryMapper {
    //查询所以的目录
    public List<ArticleCategory> findAllArticleCategories();


}
