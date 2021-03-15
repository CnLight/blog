package com.wl.blog.controller.dlp;

import com.alibaba.fastjson.JSONObject;
import com.wl.blog.service.dlp.DLPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DLPController {

    @Autowired
    DLPService dlpService;


    @ResponseBody
    @RequestMapping("/addDlpData")
    public  JSONObject addDlpData(@RequestBody JSONObject jsonObject) throws Exception {
        dlpService.addData(jsonObject.getString("keywords"));

        return jsonObject;
    }

    @RequestMapping("/searchDlpData/{keywords}")
    @ResponseBody
    public JSONObject searchDlpData(@PathVariable String keywords){
       return dlpService.findData(keywords);
    }

    @RequestMapping("/toDLPSearch")
    public String toDLPSearch(){
        return "dlp/dlpsearch";
    }
    @RequestMapping("/toDLPAdd")
    public String toDLPAdd(){
        return "dlp/dlpadd";
    }
}
