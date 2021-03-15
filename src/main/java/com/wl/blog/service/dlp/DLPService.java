package com.wl.blog.service.dlp;

import com.alibaba.fastjson.JSONObject;
import com.wl.blog.util.ParseDLPHtml;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DLPService {

    @Resource
    ParseDLPHtml parseDLPHtml;

    //添加es大力盘数据
    public void addData(String keywords) throws Exception {
        parseDLPHtml.delBuss(keywords);

    }

    //查询数据
    public JSONObject findData (String keywords){
        JSONObject jsonObject = parseDLPHtml.searchPageAll(keywords);
        return jsonObject;
    }

}
