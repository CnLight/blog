package com.wl.blog.dao;

import com.wl.blog.pojo.BlogArticle;
import com.wl.blog.pojo.Picture;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleMapper {
    //查询所有的文章
    public List<BlogArticle> findAllArticles();

    //创建一篇文章
    public int createArticle(BlogArticle blogArticle);

    //根据id查文章
    public BlogArticle findArticleById(int ArticleId);

    //分页查询
    public List<BlogArticle> findByPager(Map<String, Object> params);

    public long count();

}
