package com.wl.blog.service.admin;

import com.wl.blog.dao.ArticleMapper;
import com.wl.blog.dao.PictureMapper;
import com.wl.blog.pojo.BlogArticle;
import com.wl.blog.pojo.Pager;
import com.wl.blog.pojo.Picture;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {
    @Resource
    ArticleMapper articleMapper;
    @Resource
    PictureMapper pictureMapper;

    //查询所有的文章
    public List<BlogArticle> findAllArticles() {
        return articleMapper.findAllArticles();
    }


    //创建一篇文章
    public boolean createArticle(BlogArticle blogArticle) {
        int articleId = articleMapper.createArticle(blogArticle);
        System.out.println(articleId);
        if (articleId != 0) {
            return true;
        } else
            return false;
    }

    public BlogArticle findArticleById(int ArticleId) {
        return articleMapper.findArticleById(ArticleId);
    }

    //分页查询
    public Pager<BlogArticle> findByPager(int page, int size) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", (page - 1) * size);
        params.put("size", size);
        Pager<BlogArticle> pager = new Pager<BlogArticle>();
        List<BlogArticle> list = articleMapper.findByPager(params);
        pager.setRows(list);
        pager.setTotal(articleMapper.count());
        return pager;
    }

    //获取当前的总数页数
    public int findTotalPageNum(int pageSize) {
        double cache = articleMapper.count();
        int pageNum = (int) Math.ceil(cache / pageSize);
        return pageNum;
    }
}


