package com.wl.blog.util;

import com.alibaba.fastjson.JSONObject;
import org.json.JSONException;


import java.util.List;
import java.util.Map;

/**
 * @author wl
 * @title: LayUITableData
 * @projectName blog
 * @description: TODO
 * @date 2021/3/1019:30
 */
public class LayUITableData {
    public static JSONObject TransDataToLayUITable(List<Map<String,Object>> list) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        if(list.size()>0){
            jsonObject.put("code",0);
            jsonObject.put("msg","搜索数据成功");
            jsonObject.put("count",list.size());
            jsonObject.put("data",list);
        }
        else
        {
            jsonObject.put("code",404);
            jsonObject.put("msg","数据搜索失败请给出查询条件");
            jsonObject.put("count",list.size());
            jsonObject.put("data",null);
        }
        return jsonObject;

    }
}
