package com.wl.blog.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.wl.blog.pojo.BlogArticle;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wl
 * @title: HtmlUtil
 * @projectName blog
 * @description: TODO
 * @date 2021/3/915:51
 */
@Component
public class HtmlUtil {
    public static void main(String[] args) throws Exception {
        HtmlUtil htmlUtil = new HtmlUtil();
        int allPageNum = htmlUtil.findAllPageNum("java",1);
        System.out.println(allPageNum);


    }
        //查询索引中的数据,返回list
    public List<BlogArticle> parseCSDN(String keywords, int pageNum) throws Exception{

        JSONObject jsonObject = this.findBodyData(keywords, pageNum);
        Object result_vos = jsonObject.get("result_vos");
        JSONArray jsonArray =JSON.parseArray(result_vos.toString());
        ArrayList<BlogArticle> arrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject oneJsonObject = jsonArray.getJSONObject(i);
            String title = oneJsonObject.get("title").toString();
            String body = oneJsonObject.get("body").toString();
            String oneUrl = oneJsonObject.get("url").toString();
            BlogArticle blogArticle = new BlogArticle();
            blogArticle.setArticleName(title);
            blogArticle.setArticleContent(body);
            blogArticle.setArticlePictureUrl(oneUrl);
            arrayList.add(blogArticle);
        }

        return arrayList;


    }

    //返回一个jsonData  Page的总数
    public  int findAllPageNum(String keywords, int pageNum) throws Exception {
        JSONObject bodyData = this.findBodyData(keywords, pageNum);
        Object pageList = bodyData.get("page_list");
        JSONArray pageArr  =JSONArray.parseArray(pageList.toString());
        int pageAllNum = (int) pageArr.get(5);
        int pageSize = bodyData.getInteger("page_size");
        return pageAllNum*pageSize;

    }



    //返回JSONData
    public JSONObject findBodyData(String keywords, int pageNum) throws Exception{
        String url = "https://so.csdn.net/api/v2/search?q="+keywords+"&p="+pageNum; //创建一个webclient
        Connection.Response jsonString = (Connection.Response) Jsoup.connect(url).header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                .timeout(10000).ignoreContentType(true).execute();//.get();
        String jsonData = jsonString.body();
        JSONObject jsonObject = JSON.parseObject(jsonData);
        return jsonObject;
    }




    //获取js异步加载后的页面  暂时未用
    private static HtmlPage getHtmlPage(String url) throws Exception{
        final WebClient webClient=new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setMaxInMemory(500);
        final HtmlPage page = webClient.getPage(url);
        System.err.println("查询中，请稍候");
        TimeUnit.SECONDS.sleep(5); 	//web请求数据需要时间，必须让主线程休眠片刻
        webClient.close();

        return page;
    }


}
