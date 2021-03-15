package com.wl.blog.util;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface ParseHtml{
    //返回获取的url的json数据
    public JSONObject findDataByURL(String url , String keywords, int pageNum) throws Exception;


    //解析json数据
    public List parseData(JSONObject jsonObject);

    //根据返回的json数据解析添加进elasticsearch索引库中
    public JSONObject addJSONData(List list);


    //查询数据
    public JSONObject searchPageAll(String keywords);
}
