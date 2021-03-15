package com.wl.blog;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.wl.blog.dao.TagMapper;
import com.wl.blog.pojo.BlogArticle;
import com.wl.blog.pojo.BlogTag;
import com.wl.blog.pojo.Picture;
import com.wl.blog.service.admin.ArticleService;
import com.wl.blog.service.admin.ContentService;
import com.wl.blog.service.admin.PictureService;
import com.wl.blog.service.admin.UserService;
import com.wl.blog.util.ParseDLPHtml;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BlogApplicationTests {
 @Autowired
    ContentService contentService;
@Autowired
    ParseDLPHtml parseDLPHtml;
    @Test
    void contextLoads() throws Exception {

        System.out.println(parseDLPHtml.searchPageAll("filename=xci").toString());
    }

}
