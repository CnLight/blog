package com.wl.blog.util;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author wl
 * @title: StringUtil
 * @projectName blog
 * @description: TODO
 * @date 2021/3/1211:55
 */
public class StringUtil {
    //根据要求分割字符串
    public static Map<String, String> getValueByName(String waitStr) {
        Map<String, String> map = new HashMap<>();
            String[] waitStrArr = waitStr.split("&");
            for (int i = 0; i < waitStrArr.length; i++) {
                String[] finalStr = waitStrArr[i].split("=");
                if(finalStr.length==2)
                map.put(finalStr[0], finalStr[1]);
                else {
                    map.put(finalStr[0],"");
                }
            }
        return map;

    }
}
