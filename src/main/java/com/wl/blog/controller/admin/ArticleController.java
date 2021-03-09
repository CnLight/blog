package com.wl.blog.controller.admin;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.wl.blog.dao.TagMapper;
import com.wl.blog.pojo.ArticleCategory;
import com.wl.blog.pojo.BlogArticle;
import com.wl.blog.pojo.BlogTag;
import com.wl.blog.pojo.Picture;
import com.wl.blog.service.admin.ArticleService;
import com.wl.blog.service.admin.CategoryService;
import com.wl.blog.service.admin.PictureService;
import com.wl.blog.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/article")
public class ArticleController {
    @Resource
    ArticleService articleService;
    @Resource
    TagMapper tagMapper;
    @Resource
    CategoryService categoryService;

    @RequestMapping("/addCategory")
    public String toArticleEdit() {
        return "/article/toAddCategory";
    }

    @RequestMapping("/category")
    public String disPlayCatalog() {
        return "/article/category";
    }

    @RequestMapping("/blog")
    public String findBlog() {
        return "/blog";
    }

    @RequestMapping("/addArticle")
    public String addArticle(Model model) {
        List<ArticleCategory> allArticleCategories = categoryService.findAllArticleCategories();
        model.addAttribute("allArticleCategories", allArticleCategories);
        return "article/addArticle";
    }

    @Resource
    PictureService pictureService;

    @RequestMapping("/saveArticle")
    @ResponseBody
    public JsonResult saveArticle(BlogArticle blogArticle) {
        JsonResult jsonResult = new JsonResult();
        Picture picture = new Picture();
        //随机生成一张图片
        String path = "/img/featureimages/" + (int) (Math.random() * 23 + 1) + ".jpg";
        picture.setPicturePath(path);
        int i = pictureService.addPicture(picture);
        if (i != 0) {
            blogArticle.setArticlePictureId(i);
            blogArticle.setArticlePictureUrl(path);
        }

        // 判断摘要是否为空
        if (StrUtil.isEmpty(blogArticle.getArticleSummary())) {
            // 如果摘要为空则取前五十字为摘要
            int post_summary = 50;
            // 清理html标签和空白字符
            String summaryText = StrUtil.cleanBlank(HtmlUtil.cleanHtmlTag(blogArticle.getArticleContent()));
            // 设置文章摘要
            if (summaryText.length() > post_summary) {
                blogArticle.setArticleSummary(summaryText.substring(0, post_summary));
            } else {
                blogArticle.setArticleSummary(summaryText);
            }
        }
        //获取当前的时间
        DateTime currentTime = DateUtil.date();
        blogArticle.setArticleCreateTime(currentTime);
        blogArticle.setArticleUpdateTime(currentTime);

        boolean bl = articleService.createArticle(blogArticle);
        if (bl == true) {
            jsonResult.setFlag(true);
        } else
            jsonResult.setFlag(false);
        return jsonResult;
    }


    @RequestMapping("/post/{id}")
    public String findPost(@PathVariable int id, Model model) {
        BlogArticle blogArticle = articleService.findArticleById(id);
        model.addAttribute("blogArticle", blogArticle);
        System.out.println(blogArticle.toString());

        return "article/post";
    }

    @RequestMapping("/findTag")
    @ResponseBody
    public JSONObject findTag(String id) {
        List<BlogTag> tags = tagMapper.findTagsByCategoryId(Integer.parseInt(id));
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("tags", tags);
        return jsonObject;
    }


}

