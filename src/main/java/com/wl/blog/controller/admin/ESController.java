package com.wl.blog.controller.admin;

import com.wl.blog.service.admin.ContentService;
import com.wl.blog.util.LayUITableData;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author wl
 * @title: ESController
 * @projectName blog
 * @description: TODO
 * @date 2021/3/1012:07
 */
@Controller
public class ESController {
    @Resource
    private ContentService contentService;
    @GetMapping("/parse/{keywords}")
    @ResponseBody
    public boolean parse(@PathVariable String keywords) throws Exception {
        return contentService.addPage(keywords);
    }


    @GetMapping("/search/{keywords}/{pageNo}/{pageSize}")
    @ResponseBody
    public List<Map<String,Object>> search(@PathVariable String keywords , @PathVariable int pageNo ,
                                           @PathVariable int pageSize) throws IOException {
        return   contentService.searchPage(keywords, pageNo, pageSize);
    }

    @GetMapping("/searchLayui/{keywords}")
    @ResponseBody
        public JSONObject searchByLayUI(@PathVariable String keywords ) throws Exception {

            return LayUITableData.TransDataToLayUITable(contentService.searchPageAll(keywords));
        }

    @RequestMapping("/toSearch")
    public String toSearch(){
        return "esearch";
    }



    @RequestMapping("/addBlogData/{articleName}/{articleContent}")
    @ResponseBody
    public String addBlogData(@PathVariable String articleName ,@PathVariable String articleContent){

        return "";

    }
}
