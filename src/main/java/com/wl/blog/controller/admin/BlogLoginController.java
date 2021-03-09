package com.wl.blog.controller.admin;

import com.wl.blog.pojo.BlogArticle;
import com.wl.blog.pojo.Pager;
import com.wl.blog.service.admin.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class BlogLoginController {
    @Resource
    ArticleService articleService;

    @RequestMapping("/toBlog1")
    public String toBlog(Model model, String page) {
        if (page == null) page = "1";
        System.out.println(Integer.parseInt(page));
        Pager pager = articleService.findByPager(Integer.parseInt(page), 5);
        double cache = pager.getTotal();
        int pageNum = (int) Math.ceil(cache / 5);
        List<BlogArticle> articles = pager.getRows();
        model.addAttribute("articles", articles);
        model.addAttribute("pageNum", pageNum);
        return "blog";
    }

    @RequestMapping("/toBlog")
    public String list(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "3") int pageSize) {
        Pager pager = articleService.findByPager(pageNum, pageSize);
        System.out.println("当前页是：" + pageNum);
        pager.setCurrentPage(pageNum);
        int lastPageNum = pageNum - 1;
        int nextPageNum = pageNum + 1;
        pager.setLastPage(lastPageNum);
        pager.setNextPage(nextPageNum);
        pager.setTotalPage(articleService.findTotalPageNum(pageSize));
        List<BlogArticle> articles = pager.getRows();
        model.addAttribute("pager", pager);
        return "blog";
    }


}
